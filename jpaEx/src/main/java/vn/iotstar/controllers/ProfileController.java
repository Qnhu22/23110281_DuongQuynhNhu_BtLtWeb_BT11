package vn.iotstar.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import vn.iotstar.entity.User;
import vn.iotstar.services.IUserServices;
import vn.iotstar.services.impl.UserServices;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
    maxFileSize = 1024 * 1024 * 10,     // 10MB
    maxRequestSize = 1024 * 1024 * 50   // 50MB
)
@WebServlet(urlPatterns = {"/profile"})
public class ProfileController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IUserServices userService = new UserServices();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        req.setAttribute("user", user);
        req.getRequestDispatcher("/views/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("user");

        if (currentUser == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String fullname = req.getParameter("fullname").trim();
        String phone = req.getParameter("phone").trim();
        Part avatarPart = req.getPart("avatar");

        // Xử lý upload file avatar
        String avatarFileName = currentUser.getAvatar(); // Giữ nguyên nếu không upload mới
        if (avatarPart != null && avatarPart.getSize() > 0) {
            String originalFileName = Paths.get(avatarPart.getSubmittedFileName()).getFileName().toString();
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            avatarFileName = "avatar_" + currentUser.getUserId() + fileExtension;

            // Lưu file vào /WEB-INF/uploads/
            Path uploadPath = Paths.get(getServletContext().getRealPath("/WEB-INF/uploads"));
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            Path filePath = uploadPath.resolve(avatarFileName);
            avatarPart.write(filePath.toString());
        }

        // Cập nhật thông tin user
        currentUser.setFullname(fullname);
        currentUser.setPhone(phone);
        currentUser.setAvatar(avatarFileName);

        try {
            userService.update(currentUser);
            session.setAttribute("user", currentUser); // Cập nhật session
            resp.sendRedirect(req.getContextPath() + "/profile?success=true");
        } catch (Exception e) {
            req.setAttribute("error", "Cập nhật thất bại: " + e.getMessage());
            req.setAttribute("user", currentUser);
            req.getRequestDispatcher("/views/profile.jsp").forward(req, resp);
        }
    }
}
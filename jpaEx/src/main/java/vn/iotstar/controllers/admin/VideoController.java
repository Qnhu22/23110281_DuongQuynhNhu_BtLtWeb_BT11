package vn.iotstar.controllers.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import vn.iotstar.entity.Video;
import vn.iotstar.entity.User;
import vn.iotstar.entity.Category;
import vn.iotstar.services.IVideoServices;
import vn.iotstar.services.ICategoryServices;
import vn.iotstar.services.impl.VideoServices;
import vn.iotstar.services.impl.CategoryServices;
import vn.iotstar.utils.Constant;

@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = Constant.MAX_FILE_SIZE,
        maxRequestSize = Constant.MAX_REQUEST_SIZE)
@WebServlet(urlPatterns = {
        "/admin/videos", "/admin/video/add", "/admin/video/edit", "/admin/video/delete", "/admin/video/insert", "/admin/video/update",
        "/manager/videos", "/manager/video/add", "/manager/video/edit", "/manager/video/delete", "/manager/video/insert", "/manager/video/update",
        "/user/videos", "/user/video/add", "/user/video/edit", "/user/video/delete", "/user/video/insert", "/user/video/update"
})
public class VideoController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    IVideoServices videoService = new VideoServices();
    ICategoryServices cateService = new CategoryServices();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String url = req.getRequestURI();
        String[] parts = url.split("/");
        String rolePath = parts.length > 2 ? parts[2] : "";
        req.setAttribute("rolePath", rolePath);

        // ===== Danh sách video =====
        if (url.contains("videos")) {
            String keyword = req.getParameter("keyword"); // lấy từ form search
            List<Video> list;

            if (user.getRoleId() == 1 || user.getRoleId() == 3) {
                // Admin & User: xem tất cả
                list = (keyword != null && !keyword.isEmpty())
                        ? videoService.search(keyword)
                        : videoService.findAll();
            } else {
                // Manager: chỉ xem video của mình
                list = (keyword != null && !keyword.isEmpty())
                        ? videoService.searchByUserId(user.getUserId(), keyword)
                        : videoService.findByUserId(user.getUserId());
            }

            req.setAttribute("listvideo", list);
            req.getRequestDispatcher("/views/video/video-list.jsp").forward(req, resp);

        // ===== Thêm video =====
        } else if (url.contains("add")) {
            req.setAttribute("listcate", cateService.findAll());
            req.getRequestDispatcher("/views/video/video-add.jsp").forward(req, resp);

        // ===== Sửa video =====
        } else if (url.contains("edit")) {
            int id = Integer.parseInt(req.getParameter("id"));
            Video video = videoService.findById(id);
            if (video != null && (user.getRoleId() == 1 || video.getUser().getUserId() == user.getUserId())) {
                req.setAttribute("video", video);
                req.setAttribute("listcate", cateService.findAll());
                req.getRequestDispatcher("/views/video/video-edit.jsp").forward(req, resp);
            } else {
                resp.sendRedirect(req.getContextPath() + "/" + rolePath + "/videos");
            }

        // ===== Xóa video =====
        } else if (url.contains("delete")) {
            int id = Integer.parseInt(req.getParameter("id"));
            Video video = videoService.findById(id);

            if (video != null && (user.getRoleId() == 1 || video.getUser().getUserId() == user.getUserId())) {
                try {
                    videoService.delete(id);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            resp.sendRedirect(req.getContextPath() + "/" + rolePath + "/videos");
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String url = req.getRequestURI();
        String[] parts = url.split("/");
        String rolePath = parts.length > 2 ? parts[2] : "";

        if (url.contains("insert")) {
            Video video = new Video();
            video.setTitle(req.getParameter("title"));
            video.setDescription(req.getParameter("description"));
            video.setStatus(Integer.parseInt(req.getParameter("status")));
            video.setViews(Integer.parseInt(req.getParameter("views"))); // ✅ nhập từ form

            int cateId = Integer.parseInt(req.getParameter("categoryId"));
            video.setCategory(cateService.findById(cateId));
            video.setUser(user);

            uploadImage(req, video);

            videoService.insert(video);
            resp.sendRedirect(req.getContextPath() + "/" + rolePath + "/videos");

        } else if (url.contains("update")) {
            int videoId = Integer.parseInt(req.getParameter("videoId"));
            Video video = videoService.findById(videoId);

            if (video == null || (user.getRoleId() != 1 && video.getUser().getUserId() != user.getUserId())) {
                resp.sendRedirect(req.getContextPath() + "/" + rolePath + "/videos");
                return;
            }

            video.setTitle(req.getParameter("title"));
            video.setDescription(req.getParameter("description"));
            video.setStatus(Integer.parseInt(req.getParameter("status")));
            video.setViews(Integer.parseInt(req.getParameter("views"))); // ✅ cập nhật thủ công
            video.setCategory(cateService.findById(Integer.parseInt(req.getParameter("categoryId"))));

            uploadImage(req, video);

            videoService.update(video);
            resp.sendRedirect(req.getContextPath() + "/" + rolePath + "/videos");
        }
    }


    private void uploadImage(HttpServletRequest req, Video video) {
        String uploadPath = Constant.UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();

        try {
            Part part = req.getPart("poster"); // form field = poster
            if (part != null && part.getSize() > 0) {
                String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                String ext = filename.substring(filename.lastIndexOf(".") + 1);
                String fname = System.currentTimeMillis() + "." + ext;
                part.write(uploadPath + "/" + fname);
                video.setImages(fname);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

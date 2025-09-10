package vn.iotstar.controllers.admin;

import java.io.File;
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
import vn.iotstar.entity.Category;
import vn.iotstar.entity.User;
import vn.iotstar.services.ICategoryServices;
import vn.iotstar.services.impl.CategoryServices;
import vn.iotstar.utils.Constant;

@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = Constant.MAX_FILE_SIZE, // 40MB
        maxRequestSize = Constant.MAX_REQUEST_SIZE) // 50MB
@WebServlet(urlPatterns = {
        "/admin/home", "/admin/categories", "/admin/category/edit", "/admin/category/insert", "/admin/category/add", "/admin/category/delete", "/admin/category/update",
        "/manager/home", "/manager/categories", "/manager/category/edit", "/manager/category/insert", "/manager/category/add", "/manager/category/delete", "/manager/category/update",
        "/user/home", "/user/categories", "/user/category/edit", "/user/category/insert", "/user/category/add", "/user/category/delete", "/user/category/update"
})
public class CategoryController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    ICategoryServices cateService = new CategoryServices();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, java.io.IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String url = req.getRequestURI();
     // URL ví dụ: /jpaEx/admin/home → parts = ["", "jpaEx", "admin", "home"]
        String[] parts = url.split("/");
        String rolePath = parts.length > 2 ? parts[2] : ""; 

        req.setAttribute("rolePath", rolePath);

        if (url.contains("home") || url.contains("categories")) {
            List<Category> list;
            if (user.getRoleId() == 1 || user.getRoleId() == 3) { // admin or user: all
                list = cateService.findAll();
            } else { // manager: own
                list = cateService.findByUserId(user.getUserId());
            }
            req.setAttribute("listcate", list);
            String jspPath;
            if (user.getRoleId() == 1) {
                jspPath = Constant.Path.ADMIN_CATEGORIES;
            } else if (user.getRoleId() == 2) {
                jspPath = Constant.Path.MANAGER_HOME;
            } else {
                jspPath = Constant.Path.USER_HOME;
            }
            req.getRequestDispatcher(jspPath).forward(req, resp);
        } else if (url.contains("edit")) {
            int id = Integer.parseInt(req.getParameter("id"));
            Category category = cateService.findById(id);
            if (category != null && (user.getRoleId() == 1 || category.getUser().getUserId() == user.getUserId())) {
                req.setAttribute("cate", category);
                req.getRequestDispatcher(Constant.Path.CATEGORY_EDIT).forward(req, resp); // Use same edit for all
            } else {
                resp.sendRedirect(req.getContextPath() + "/" + rolePath + "/home");
            }
        } else if (url.contains("add")) {
            req.getRequestDispatcher(Constant.Path.CATEGORY_ADD).forward(req, resp); // Use same add for all
        } else if (url.contains("delete")) {
            int id = Integer.parseInt(req.getParameter("id"));
            Category category = cateService.findById(id);
            if (category != null && (user.getRoleId() == 1 || category.getUser().getUserId() == user.getUserId())) {
                try {
                    cateService.delete(id); // Xử lý ngoại lệ ở đây
                } catch (Exception e) {
                    e.printStackTrace();
                    resp.sendRedirect(req.getContextPath() + "/" + rolePath + "/home?error=delete");
                    return;
                }
            }
            resp.sendRedirect(req.getContextPath() + "/" + rolePath + "/home");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, java.io.IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String url = req.getRequestURI();
     // URL ví dụ: /jpaEx/admin/home → parts = ["", "jpaEx", "admin", "home"]
        String[] parts = url.split("/");
        String rolePath = parts.length > 2 ? parts[2] : ""; 


        if (url.contains("update")) {
            int categoryid = Integer.parseInt(req.getParameter("categoryid"));
            String categoryname = req.getParameter("categoryname");
            int status = Integer.parseInt(req.getParameter("status"));

            Category category = cateService.findById(categoryid);
            if (category == null || (user.getRoleId() != 1 && category.getUser().getUserId() != user.getUserId())) {
                resp.sendRedirect(req.getContextPath() + "/" + rolePath + "/home");
                return;
            }

            category.setCategoryname(categoryname);
            category.setStatus(status);

            String fname = "";
            String uploadPath = Constant.UPLOAD_DIRECTORY;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdir();

            try {
                Part part = req.getPart("images");
                if (part.getSize() > 0) {
                    String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    int index = filename.lastIndexOf(".");
                    String ext = filename.substring(index + 1);
                    fname = System.currentTimeMillis() + "." + ext;
                    part.write(uploadPath + "/" + fname);
                    category.setImages(fname);
                } else {
                    category.setImages(category.getImages());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            cateService.update(category);
            resp.sendRedirect(req.getContextPath() + "/" + rolePath + "/home");
        } else if (url.contains("insert")) {
            Category category = new Category();
            String categoryname = req.getParameter("categoryname");
            int status = Integer.parseInt(req.getParameter("status"));
            category.setCategoryname(categoryname);
            category.setStatus(status);
            category.setUser(user);

            String fname = "";
            String uploadPath = Constant.UPLOAD_DIRECTORY;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdir();

            try {
                Part part = req.getPart("images");
                if (part.getSize() > 0) {
                    String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    int index = filename.lastIndexOf(".");
                    String ext = filename.substring(index + 1);
                    fname = System.currentTimeMillis() + "." + ext;
                    part.write(uploadPath + "/" + fname);
                    category.setImages(fname);
                } else {
                    category.setImages(Constant.DEFAULT_FILENAME); // Sử dụng hằng số mặc định
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            cateService.insert(category);
            resp.sendRedirect(req.getContextPath() + "/" + rolePath + "/home");
        }
    }
}
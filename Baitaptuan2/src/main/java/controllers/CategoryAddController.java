package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Category;
import model.User;
import services.CategoryService;
import services.impl.CategoryServiceImpl;

import java.io.IOException;

@WebServlet(urlPatterns = "/category/add")
public class CategoryAddController extends HttpServlet {
    private static final long serialVersionUID = 1L; // Thêm dòng này
    private CategoryService service = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("account");
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        req.getRequestDispatcher("/views/add-category.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        User user = (User) req.getSession().getAttribute("account");
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        int userId = user.getId();

        String cateName = req.getParameter("cateName");
        String icons = req.getParameter("icons");

        Category category = new Category();
        category.setCateName(cateName);
        category.setIcons(icons);
        category.setUserId(userId);
        service.insert(category);

        resp.sendRedirect(req.getContextPath() + "/category/list");
    }
}
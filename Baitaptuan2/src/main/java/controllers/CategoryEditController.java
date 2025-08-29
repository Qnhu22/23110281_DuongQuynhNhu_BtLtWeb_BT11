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

@WebServlet(urlPatterns = "/category/edit")
public class CategoryEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CategoryService service = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("account");
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        int cateId = Integer.parseInt(req.getParameter("cateId"));
        Category category = service.get(cateId);
        if (category != null && category.getUserId() == user.getId()) {
            req.setAttribute("category", category);
            req.getRequestDispatcher("/views/edit-category.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/category/list");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        User user = (User) req.getSession().getAttribute("account");
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        int cateId = Integer.parseInt(req.getParameter("cateId"));
        String cateName = req.getParameter("cateName");
        String icons = req.getParameter("icons");

        Category category = service.get(cateId);
        if (category != null && category.getUserId() == user.getId()) {
            category.setCateName(cateName);
            category.setIcons(icons);
            service.update(category);
        }
        resp.sendRedirect(req.getContextPath() + "/category/list");
    }
}
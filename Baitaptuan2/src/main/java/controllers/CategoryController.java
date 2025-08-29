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
import java.util.List;

@WebServlet(urlPatterns = {"/category/list", "/category/delete"})
public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CategoryService service = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        User user = (User) req.getSession().getAttribute("account"); // Lấy user từ session
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        int userId = user.getId();

        if (action.equals("/category/list")) {
            List<Category> list = service.getAllByUser(userId);
            req.setAttribute("cateList", list);
            req.getRequestDispatcher("/views/list-category.jsp").forward(req, resp);
        } else if (action.equals("/category/delete")) {
            int cateId = Integer.parseInt(req.getParameter("cateId"));
            service.delete(cateId);
            resp.sendRedirect(req.getContextPath() + "/category/list");
        }
    }
}
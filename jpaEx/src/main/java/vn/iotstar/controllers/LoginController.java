package vn.iotstar.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.entity.User;
import vn.iotstar.services.IUserServices;
import vn.iotstar.services.impl.UserServices;

import java.io.IOException;

@WebServlet(urlPatterns = {"/login", "/logout"})
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private IUserServices userService = new UserServices();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String url = req.getRequestURI();

        if (url.contains("logout")) {
            HttpSession session = req.getSession();
            session.invalidate(); // xoá session
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username").trim();
        String password = req.getParameter("password").trim();

        User user = userService.login(username, password);

        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);

            // điều hướng theo roleId
            switch (user.getRoleId()) {
                case 1: // admin
                    resp.sendRedirect(req.getContextPath() + "/admin/categories");
                    break;
                case 2: // manager
                    resp.sendRedirect(req.getContextPath() + "/manager/home");
                    break;
                case 3: // user
                    resp.sendRedirect(req.getContextPath() + "/user/home");
                    break;
                default:
                    req.setAttribute("error", "Role không hợp lệ!");
                    req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
                    break;
            }
        } else {
            req.setAttribute("error", "Sai username hoặc password!");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        }
    }
}
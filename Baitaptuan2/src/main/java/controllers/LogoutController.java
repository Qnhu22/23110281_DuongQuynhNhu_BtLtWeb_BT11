package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(urlPatterns = "/logout")
public class LogoutController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Lấy session
        HttpSession session = req.getSession(false);
        if (session != null) {
            // Xóa session
            session.invalidate();
        }

        // Xóa cookie "username" nếu có
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())) {
                    cookie.setMaxAge(0); // Xóa cookie
                    cookie.setPath("/");
                    resp.addCookie(cookie);
                    break;
                }
            }
        }

        // Chuyển hướng về trang đăng nhập
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}
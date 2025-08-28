package baitapsession;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/profile")
public class ProfileSvlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html; charset=UTF-8");
        HttpSession session = req.getSession(false);

        if (session != null && session.getAttribute("name") != null) {
            String name = (String) session.getAttribute("name");
            PrintWriter out = resp.getWriter();
            out.println("<!DOCTYPE html><html><head><meta charset='UTF-8'><title>Profile</title></head><body>");
            out.println("<h2>Chào bạn, " + name + " đến với trang quản lý tài khoản</h2>");
            out.println("<p><a href='" + req.getContextPath() + "/logout'>Đăng xuất</a></p>");
            out.println("</body></html>");
        } else {
            resp.sendRedirect(req.getContextPath() + "/LoginSession.html");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}

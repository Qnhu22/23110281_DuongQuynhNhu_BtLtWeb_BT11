package baitapsession;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/loginNew")
public class LoginSvlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        if ("nhu".equals(username) && "123".equals(password)) {
            out.println("<h3>Chào mừng bạn, " + username + "</h3>");
            HttpSession session = request.getSession();
            session.setAttribute("name", username);
            out.println("<a href='profile'>Vào trang profile</a><br>");
            out.println("<a href='logout'>Đăng xuất</a>");
        } else {
            out.println("<h3 style='color:red'>Tài khoản hoặc mật khẩu không chính xác</h3>");
            request.getRequestDispatcher("LoginSession.html").include(request, response);
        }
    }
}
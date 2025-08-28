package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.UserService;
import services.impl.UserServiceImpl;

import java.io.IOException;

@WebServlet(urlPatterns = "/register")
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static final String REGISTER = "/views/register.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(REGISTER).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String fullname = req.getParameter("fullname");
        String phone = req.getParameter("phone");

        UserService service = new UserServiceImpl();
        String alertMsg = "";

        if (service.checkExistEmail(email)) {
            alertMsg = "Email đã tồn tại!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher(REGISTER).forward(req, resp);
            return;
        }

        if (service.checkExistUsername(username)) {
            alertMsg = "Tên đăng nhập đã tồn tại!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher(REGISTER).forward(req, resp);
            return;
        }

        if (service.checkExistPhone(phone)) {
            alertMsg = "Số điện thoại đã tồn tại!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher(REGISTER).forward(req, resp);
            return;
        }

        if (service.register(username, password, email, fullname, phone)) {
            alertMsg = "Đăng ký thành công. Vui lòng đăng nhập!";
            req.setAttribute("alert", alertMsg);
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            alertMsg = "Đăng ký thất bại. Vui lòng thử lại!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher(REGISTER).forward(req, resp);
        }
    }
}
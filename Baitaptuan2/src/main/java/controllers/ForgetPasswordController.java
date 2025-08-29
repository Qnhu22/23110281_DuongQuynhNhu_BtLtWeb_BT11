package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import org.apache.commons.lang3.RandomStringUtils;
import services.UserService;
import services.impl.UserServiceImpl;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@WebServlet(urlPatterns = "/forget-password")
public class ForgetPasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserService service = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/forget-password.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String email = req.getParameter("email");
        User user = service.getByEmail(email);  // Bây giờ đã nhận ra phương thức

        if (user != null) {
            // Tạo mật khẩu ngẫu nhiên (10 ký tự)
            String newPassword = RandomStringUtils.randomAlphanumeric(10);
            // Cập nhật mật khẩu mới vào cơ sở dữ liệu
            service.updatePassword(email, newPassword);

            // Gửi email với mật khẩu mới
            String subject = "Mật khẩu mới của bạn";
            String message = "Mật khẩu mới của bạn là: " + newPassword + ". Vui lòng thay đổi sau khi đăng nhập.";
            sendEmail(email, subject, message);

            req.setAttribute("alert", "Mật khẩu mới đã được gửi đến email của bạn!");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        } else {
            req.setAttribute("alert", "Email không tồn tại trong hệ thống!");
            req.getRequestDispatcher("/views/forget-password.jsp").forward(req, resp);
        }
    }

    private void sendEmail(String toEmail, String subject, String message) {
        String host = "smtp.gmail.com";
        String port = "587";
        String senderEmail = "your-email@gmail.com";
        String senderPassword = "your-app-password";

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(senderEmail));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            msg.setSubject(subject);
            msg.setText(message);
            Transport.send(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
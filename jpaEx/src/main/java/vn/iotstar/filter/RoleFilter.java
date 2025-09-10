package vn.iotstar.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.entity.User;

@WebFilter(urlPatterns = {"/admin/*", "/manager/*", "/user/*"})
public class RoleFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        User user = (session != null) ? (User) session.getAttribute("user") : null;
        String path = httpRequest.getServletPath();

        if (user == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
            return;
        }

        int roleId = user.getRoleId();

        if (path.startsWith("/admin/") && roleId != 1) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
            return;
        } else if (path.startsWith("/manager/") && roleId != 2) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
            return;
        } else if (path.startsWith("/user/") && roleId != 3) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void init(jakarta.servlet.FilterConfig filterConfig) throws ServletException {
        // Empty
    }

    @Override
    public void destroy() {
        // Empty
    }
}
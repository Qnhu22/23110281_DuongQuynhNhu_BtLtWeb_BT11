<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="entity.User" %>
<%
    User user = (User) session.getAttribute("user");
%>
<div class="bg-light p-2 border-bottom">
    <% if (user != null) { %>
        Xin chào, <strong><%= user.getFullname() != null ? user.getFullname() : user.getUsername() %></strong>
    <% } else { %>
        Xin chào, Khách
    <% } %>
</div>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="entity.User" %>
<%
  User u = (User) session.getAttribute("user");
%>
<h2>Thông tin cá nhân</h2>

<% if (request.getAttribute("msg") != null) { %>
  <div class="alert alert-success"><%= request.getAttribute("msg") %></div>
<% } %>

<form action="${pageContext.request.contextPath}/profile" method="post" enctype="multipart/form-data">
  <div class="mb-3">
    <label>Họ tên:</label>
    <input type="text" name="fullname" value="<%= (u!=null?u.getFullname():"") %>" class="form-control"/>
  </div>
  <div class="mb-3">
    <label>Số điện thoại:</label>
    <input type="text" name="phone" value="<%= (u!=null?u.getPhone():"") %>" class="form-control"/>
  </div>
  <div class="mb-3">
    <label>Ảnh đại diện:</label>
    <input type="file" name="image" class="form-control"/>
    <% if (u!=null && u.getImage()!=null) { %>
      <img src="<%= request.getContextPath() + "/" + u.getImage() %>" width="100"/>
    <% } %>
  </div>
  <button type="submit" class="btn btn-primary">Cập nhật</button>
</form>

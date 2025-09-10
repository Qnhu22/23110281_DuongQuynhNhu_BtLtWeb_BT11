<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Đăng nhập</title>
</head>
<body>
    <h2>Đăng nhập</h2>
    <form action="<c:url value='/login'/>" method="post">
        <c:if test="${not empty error}">
            <h3 style="color:red">${error}</h3>
        </c:if>
        <label>Tài khoản:</label>
        <input type="text" name="username" required><br><br>
        <label>Mật khẩu:</label>
        <input type="password" name="password" required><br><br>
        <button type="submit">Đăng nhập</button>
    </form>
    <p><a href="<c:url value='/register'/>">Đăng ký tài khoản</a></p>
<p><a href="<c:url value='/forgot'/>">Quên mật khẩu?</a></p>
</body>
</html>
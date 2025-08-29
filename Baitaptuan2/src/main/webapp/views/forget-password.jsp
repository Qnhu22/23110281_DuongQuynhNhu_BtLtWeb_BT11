<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quên mật khẩu</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/forget-password" method="post">
        <h2>Quên mật khẩu</h2>
        <c:if test="${alert != null}">
            <h3>${alert}</h3>
        </c:if>
        <label>Email:</label>
        <input type="email" name="email" required><br>
        <button type="submit">Gửi mật khẩu mới</button>
    </form>
    <a href="${pageContext.request.contextPath}/login">Quay lại đăng nhập</a>
</body>
</html>
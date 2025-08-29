<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng ký</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/register" method="post">
        <h2>Đăng ký tài khoản</h2>
        <c:if test="${alert != null}">
            <h3 class="alert">${alert}</h3>
        </c:if>
        <section>
            <label>Tên đăng nhập:</label>
            <input type="text" name="username" required>
        </section>
        <section>
            <label>Mật khẩu:</label>
            <input type="password" name="password" required>
        </section>
        <section>
            <label>Email:</label>
            <input type="email" name="email" required>
        </section>
        <section>
            <label>Họ và tên:</label>
            <input type="text" name="fullname" required>
        </section>
        <section>
            <label>Số điện thoại:</label>
            <input type="text" name="phone" required>
        </section>
        <button type="submit">Đăng ký</button>
    </form>
</body>
</html>
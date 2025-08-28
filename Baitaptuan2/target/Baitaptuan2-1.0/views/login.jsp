<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head><title>Login</title></head>
<body>
    <form action="login" method="post">
        <h2>Đăng nhập</h2>
        <c:if test="${alert != null}">
            <h3 style="color:red">${alert}</h3>
        </c:if>
        <label>Tài khoản:</label>
        <input type="text" name="username"/><br/>
        <label>Mật khẩu:</label>
        <input type="password" name="password"/><br/>
        <button type="submit">Login</button>
    </form>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Đăng ký</title>
</head>
<body>
    <h2>Đăng ký tài khoản</h2>
    <form action="<c:url value='/register'/>" method="post">
        <label>Username:</label><br>
        <input type="text" name="username" required><br><br>

        <label>Password:</label><br>
        <input type="password" name="password" required><br><br>

        <label>Email:</label><br>
        <input type="email" name="email" required><br><br>

        <label>Role:</label><br>
        <select name="roleId">
            <option value="3">User</option>
            <option value="2">Manager</option>
        </select><br><br>

        <input type="submit" value="Đăng ký">
    </form>
    <p>Đã có tài khoản? <a href="<c:url value='/login'/>">Đăng nhập</a></p>
</body>
</html>

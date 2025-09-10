<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Quên mật khẩu</title>
</head>
<body>
    <h2>Quên mật khẩu</h2>

    <c:if test="${not empty message}">
        <p style="color:green">${message}</p>
    </c:if>

    <form action="<c:url value='/forgot'/>" method="post">
        <label>Email:</label><br>
        <input type="email" name="email" required><br><br>
        <input type="submit" value="Gửi lại mật khẩu">
    </form>

    <p><a href="<c:url value='/login'/>">Quay lại đăng nhập</a></p>
</body>
</html>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head><title>Manager Home</title></head>
<body>
    <h1>Chào mừng Manager ${username}!</h1>
    <form action="${pageContext.request.contextPath}/logout" method="post">
        <button type="submit">Đăng xuất</button>
    </form>
    <section>
    <a href="${pageContext.request.contextPath}/category/list">Quản lý Category</a>
    </section>
</body>
</html>
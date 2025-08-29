<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head><title>Sửa Category</title></head>
<body>
    <form action="${pageContext.request.contextPath}/category/edit" method="post">
        <input type="hidden" name="cateId" value="${category.cateId}">
        <label>Tên Category:</label>
        <input type="text" name="cateName" value="${category.cateName}" required><br>
        <label>Icon:</label>
        <input type="text" name="icons" value="${category.icons}"><br>
        <button type="submit">Sửa</button>
    </form>
</body>
</html>
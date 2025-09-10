<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sửa Category</title>
</head>
<body>
    <h2>Sửa Category</h2>
    <form action="<c:url value='/admin/category/update'/>" method="post" enctype="multipart/form-data">
        <input type="hidden" name="categoryid" value="${cate.categoryId}">

        <label>Tên Category:</label><br>
        <input type="text" name="categoryname" value="${cate.categoryname}" required><br><br>

        <label>Hình ảnh hiện tại:</label><br>
        <c:if test="${not empty cate.images}">
            <img src="<c:url value='/image?fname=${cate.images}'/>" width="150" height="150"/><br>
        </c:if>
        <input type="file" name="images"><br><br>

        <label>Trạng thái:</label><br>
        <input type="radio" name="status" value="1" <c:if test="${cate.status == 1}">checked</c:if>> Đang hoạt động
        <input type="radio" name="status" value="0" <c:if test="${cate.status == 0}">checked</c:if>> Khóa<br><br>

        <input type="submit" value="Cập nhật">
    </form>
</body>
</html>

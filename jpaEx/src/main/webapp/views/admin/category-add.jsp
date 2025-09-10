<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Thêm Category</title>
</head>
<body>
    <h2>Thêm Category</h2>
    <form action="<c:url value='/admin/category/insert'/>" method="post" enctype="multipart/form-data">

        <label>Tên Category:</label><br>
        <input type="text" name="categoryname" required><br><br>

        <label>Hình ảnh:</label><br>
        <input type="file" name="images"><br><br>

        <label>Trạng thái:</label><br>
        <input type="radio" name="status" value="1" checked> Đang hoạt động
        <input type="radio" name="status" value="0"> Khóa<br><br>

        <input type="submit" value="Thêm mới">
    </form>
</body>
</html>
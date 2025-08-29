<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Thêm Category</title>
    <!-- Có thể thêm CSS để định dạng nút nếu muốn -->
    <style>
        .button-group {
            margin-top: 10px;
        }
        .button-group button, .button-group a {
            padding: 5px 10px;
            margin-right: 10px;
            text-decoration: none;
            color: white;
            background-color: #007BFF;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .button-group a:hover, .button-group button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <form action="${pageContext.request.contextPath}/category/add" method="post">
        <label>Tên Category:</label>
        <input type="text" name="cateName" required><br>
        <label>Icon:</label>
        <input type="text" name="icons"><br>
        <div class="button-group">
            <button type="submit">Thêm</button>
            <a href="${pageContext.request.contextPath}/category/list">Quay lại</a>
        </div>
    </form>
</body>
</html>
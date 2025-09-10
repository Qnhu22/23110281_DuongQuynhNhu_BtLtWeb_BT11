<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manager Home</title>
</head>
<body>
    <h2>Trang Manager</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Tên Category</th>
            <th>Trạng thái</th>
        </tr>
        <c:forEach items="${listcate}" var="cate">
            <tr>
                <td>${cate.categoryId}</td>
                <td>${cate.categoryname}</td>
                <td>
                    <c:choose>
                        <c:when test="${cate.status == 1}">Đang hoạt động</c:when>
                        <c:otherwise>Khóa</c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head><title>Danh sách Category</title></head>
<body>
    <h1>Danh sách Category của bạn</h1>
    <a href="${pageContext.request.contextPath}/category/add">Thêm Category</a>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Tên</th>
            <th>Icon</th>
            <th>Hành động</th>
        </tr>
        <c:forEach items="${cateList}" var="cate">
            <tr>
                <td>${cate.cateId}</td>
                <td>${cate.cateName}</td>
                <td>${cate.icons}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/category/edit?cateId=${cate.cateId}">Sửa</a>
                    <a href="${pageContext.request.contextPath}/category/delete?cateId=${cate.cateId}">Xóa</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${param.error == 'permission'}">
    <h3 style="color:red">Bạn không có quyền thực hiện hành động này!</h3>
</c:if>
<c:if test="${param.error == 'delete'}">
    <h3 style="color:red">Xóa category thất bại!</h3>
</c:if>
<c:if test="${param.error == 'upload'}">
    <h3 style="color:red">Tải lên ảnh thất bại!</h3>
</c:if>
<c:if test="${param.error == 'invalid_format'}">
    <h3 style="color:red">Chỉ chấp nhận file ảnh (jpg, jpeg, png, gif)!</h3>
</c:if>

<!DOCTYPE html>
<html>
<head>
    <title>Manager Home</title>
</head>
<body>
    <h2>Trang Manager</h2>
    <p><a href="<c:url value='/logout'/>">Đăng xuất</a></p>
    <a href="<c:url value='/manager/category/add'/>">Thêm category</a>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Tên Category</th>
            <th>Trạng thái</th>
            <th>Hành động</th>
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
                <td>
                    <a href="<c:url value='/manager/category/edit?id=${cate.categoryId}'/>">Sửa</a> |
                    <a href="<c:url value='/manager/category/delete?id=${cate.categoryId}'/>"
                       onclick="return confirm('Bạn có chắc muốn xóa?')">Xóa</a>

                       
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
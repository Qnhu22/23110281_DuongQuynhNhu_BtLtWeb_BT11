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
    <title>Danh sách Category</title>
</head>
<body>
    <h2>Quản lý Category (Admin)</h2>
    <p><a href="<c:url value='/logout'/>">Đăng xuất</a></p>
    <a href="<c:url value='/admin/category/add'/>">Thêm category</a>
    <table border="1">
        <tr>
            <th>STT</th>
            <th>Hình ảnh</th>
            <th>Tên Category</th>
            <th>Trạng thái</th>
            <th>Hành động</th>
        </tr>
        <c:forEach items="${listcate}" var="cate" varStatus="stt">
            <tr>
                <td>${stt.index + 1}</td>
                <td>
                    <c:if test="${not empty cate.images}">
                        <img src="<c:url value='/image?fname=${cate.images}'/>" width="80" height="80"/>
                    </c:if>
                </td>
                <td>${cate.categoryname}</td>
                <td>
                    <c:choose>
                        <c:when test="${cate.status == 1}">Đang hoạt động</c:when>
                        <c:otherwise>Khóa</c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <c:if test="${sessionScope.user.roleId == 1 || cate.user.userId == sessionScope.user.userId}">
                        <a href="<c:url value='/admin/category/edit?id=${cate.categoryId}'/>">Sửa</a> |
                        <a href="<c:url value='/admin/category/delete?id=${cate.categoryId}'/>"
                           onclick="return confirm('Bạn có chắc muốn xóa?')">Xóa</a>
             
                        
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
<div class="container mt-4">
    <h2 class="mb-4 text-primary">Cập nhật thông tin cá nhân</h2>

    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>
    <c:if test="${param.success eq 'true'}">
        <div class="alert alert-success">Cập nhật thành công!</div>
    </c:if>

    <form action="${pageContext.request.contextPath}/profile" method="post" enctype="multipart/form-data" class="card p-4 shadow-sm">
        <div class="mb-3">
            <label class="form-label">Họ tên</label>
            <input type="text" name="fullname" class="form-control" value="${user.fullname}" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Số điện thoại</label>
            <input type="text" name="phone" class="form-control" value="${user.phone}">
        </div>

        <div class="mb-3">
            <label class="form-label">Ảnh đại diện</label><br/>
            <c:if test="${not empty user.avatar}">
                <img src="${pageContext.request.contextPath}/image?fname=${user.avatar}" width="120" height="120" class="rounded-circle mb-2"/>
            </c:if>
            <input type="file" name="avatar" class="form-control">
        </div>

        <button type="submit" class="btn btn-primary">Cập nhật</button>
        <a href="${pageContext.request.contextPath}/" class="btn btn-secondary">Quay lại</a>
    </form>
</div>
</body>
</html>
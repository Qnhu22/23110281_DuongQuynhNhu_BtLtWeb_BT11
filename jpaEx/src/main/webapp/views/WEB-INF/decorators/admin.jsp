<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><sitemesh:write property="title" default="Trang chủ Admin"/> - Quản lý</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <style>
        body { padding-top: 70px; background-color: #e7f0fd; color: #000000; }
        .navbar { background-color: #0d6efd !important; }
        .navbar-brand { font-size: 1.5rem; color: white !important; }
    </style>
    <sitemesh:write property="head"/>
</head>
<body>
    <%@ include file="/commons/web/header.jsp" %>
    <main class="container mt-4">
        <sitemesh:write property="body"/>
    </main>
    <%@ include file="/commons/web/footer.jsp" %>
</body>
</html>
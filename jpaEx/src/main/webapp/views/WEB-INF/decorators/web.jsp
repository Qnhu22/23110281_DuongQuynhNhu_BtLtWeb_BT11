<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><sitemesh:write property="title" default="Welcome"/> - My Website</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <style>
        body { padding-top: 70px; }
        .navbar-brand { font-size: 1.5rem; }
    </style>
    <sitemesh:write property="head"/>
</head>
<body>
    <div>
        <%@ include file="/commons/web/header.jsp"%>
    </div>
    <div>
        <sitemesh:write property="body"/>
    </div>
    <div>
        <%@ include file="/commons/web/footer.jsp"%>
    </div>
</body>
</html>
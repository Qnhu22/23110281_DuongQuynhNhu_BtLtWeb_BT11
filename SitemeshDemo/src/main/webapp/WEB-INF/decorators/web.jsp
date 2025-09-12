<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title><sitemesh:write property="title" /></title>
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet" />
</head>
<body>
	<!-- Header -->
	<jsp:include page="/common/web/header.jsp" />

	<!-- Topbar -->
	<jsp:include page="/common/web/topbar.jsp" />

	<div class="container-fluid mt-3">
		<div class="row">
			<!-- Left sidebar -->
			<div class="col-md-3">
				<jsp:include page="/common/web/left.jsp" />
			</div>

			<!-- Main content -->
			<div class="col-md-9">
				<sitemesh:write property="body" />
			</div>
		</div>
	</div>

	<!-- Footer -->
	<jsp:include page="/common/web/footer.jsp" />

	<!-- Bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

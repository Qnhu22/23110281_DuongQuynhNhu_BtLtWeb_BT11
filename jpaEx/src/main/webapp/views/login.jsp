<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Đăng nhập</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        body {
            background: linear-gradient(135deg, #0d6efd 60%, #ff80ab 40%); /* xanh biển + hồng */
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            font-family: Arial, sans-serif;
        }
        .card {
            border-radius: 16px;
            box-shadow: 0px 6px 20px rgba(0,0,0,0.2);
            background: #fff;
            width: 400px;
            padding: 30px;
            position: relative;
        }
        h2 {
            color: #0d6efd;
            font-weight: bold;
            text-align: center;
            margin-bottom: 20px;
        }
        .btn-primary {
            background-color: #0d6efd;
            border: none;
        }
        .btn-primary:hover {
            background-color: #0b5ed7;
        }
        .decoration {
            position: absolute;
            width: 80px;
            height: 80px;
            border-radius: 50%;
            background: #ff80ab;
            top: -20px;
            right: -20px;
            z-index: -1;
            box-shadow: 0px 4px 12px rgba(0,0,0,0.1);
        }
    </style>
</head>
<body>
<div class="card">
    <div class="decoration"></div>
    <h2>Đăng nhập</h2>

    <c:if test="${not empty error}">
      <div class="alert alert-danger">${error}</div>
    </c:if>
    <c:if test="${param.success == 'register'}">
      <div class="alert alert-success">Đăng ký thành công! Vui lòng đăng nhập.</div>
    </c:if>

    <form action="<c:url value='/login'/>" method="post">
      <div class="mb-3">
        <label class="form-label">Tài khoản</label>
        <input type="text" name="username" class="form-control" required>
      </div>
      <div class="mb-3">
        <label class="form-label">Mật khẩu</label>
        <input type="password" name="password" class="form-control" required>
      </div>
      <button type="submit" class="btn btn-primary w-100">Đăng nhập</button>
    </form>

    <div class="d-flex justify-content-between mt-3">
      <a href="<c:url value='/register'/>" style="color:#0d6efd;">Đăng ký</a>
      <a href="<c:url value='/forgot'/>" style="color:#ff80ab;">Quên mật khẩu?</a>
    </div>
</div>
</body>
</html>

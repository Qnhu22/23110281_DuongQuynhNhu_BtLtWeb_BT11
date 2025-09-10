<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Đăng ký</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(135deg, #0d6efd 60%, #ff80ab 40%);
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
            width: 450px;
            padding: 30px;
            position: relative;
        }
        h2 { color: #0d6efd; text-align: center; font-weight: bold; }
        .btn-primary { background-color: #0d6efd; border: none; }
        .btn-primary:hover { background-color: #0b5ed7; }
        .decoration {
            position: absolute; width: 80px; height: 80px; border-radius: 50%;
            background: #ff80ab; top: -20px; right: -20px; z-index: -1;
        }
    </style>
</head>
<body>
<div class="card">
  <div class="decoration"></div>
  <h2>Đăng ký tài khoản</h2>
  <form action="<c:url value='/register'/>" method="post">
    <div class="mb-3">
      <label class="form-label">Email</label>
      <input type="email" name="email" class="form-control" required>
    </div>
    <div class="mb-3">
      <label class="form-label">Tên tài khoản</label>
      <input type="text" name="username" class="form-control" required>
    </div>
    <div class="mb-3">
      <label class="form-label">Mật khẩu</label>
      <input type="password" name="password" class="form-control" required>
    </div>
    <div class="mb-3">
      <label class="form-label">Vai trò</label>
      <select name="roleId" class="form-select">
        <option value="1">Admin</option>
        <option value="2">Manager</option>
        <option value="3">User</option>
      </select>
    </div>
    <button type="submit" class="btn btn-primary w-100">Đăng ký</button>
  </form>
  <div class="mt-3 text-center">
    <a href="<c:url value='/login'/>" style="color:#ff80ab;">Quay lại đăng nhập</a>
  </div>
</div>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Thêm Category</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f4f9ff; font-family: Arial, sans-serif; }
        .container { max-width: 600px; margin-top: 50px; }
        h2 { color: #0d6efd; }
    </style>
</head>
<body>
<div class="container">
  <h2 class="mb-4">Thêm Category</h2>
  <form action="<c:url value='/${rolePath}/category/insert'/>" method="post" enctype="multipart/form-data" class="card p-4">
    <div class="mb-3">
      <label class="form-label">Tên Category</label>
      <input type="text" name="categoryname" class="form-control" required>
    </div>
    <div class="mb-3">
      <label class="form-label">Hình ảnh</label>
      <input type="file" name="images" class="form-control">
    </div>
    <div class="mb-3">
      <label class="form-label">Trạng thái</label><br>
      <input type="radio" name="status" value="1" checked> Đang hoạt động
      <input type="radio" name="status" value="0"> Khóa
    </div>
    <button type="submit" class="btn btn-primary">Thêm mới</button>
    <a href="<c:url value='/${rolePath}/home'/>" class="btn btn-secondary">Hủy</a>
  </form>
</div>
</body>
</html>

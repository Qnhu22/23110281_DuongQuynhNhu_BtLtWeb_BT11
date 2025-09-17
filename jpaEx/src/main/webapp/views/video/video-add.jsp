<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Thêm Video</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
  <h2 class="text-primary">Thêm Video</h2>

  <form action="<c:url value='/${rolePath}/video/insert'/>" method="post" enctype="multipart/form-data" class="mt-3">

    <!-- Title -->
    <div class="mb-3">
      <label class="form-label">Tiêu đề</label>
      <input type="text" name="title" class="form-control" required>
    </div>

    <!-- Description -->
    <div class="mb-3">
      <label class="form-label">Mô tả</label>
      <textarea name="description" class="form-control"></textarea>
    </div>

    <!-- Poster -->
    <div class="mb-3">
      <label class="form-label">Ảnh đại diện</label>
      <input type="file" name="poster" class="form-control">
    </div>

    <!-- Category -->
    <div class="mb-3">
      <label class="form-label">Danh mục</label>
      <select name="categoryId" class="form-select" required>
        <c:forEach items="${listcate}" var="cate">
          <option value="${cate.categoryId}">${cate.categoryname}</option>
        </c:forEach>
      </select>
    </div>

    <!-- Views -->
    <div class="mb-3">
      <label class="form-label">Số lượt xem</label>
      <input type="number" name="views" class="form-control" value="0" min="0">
    </div>

    <!-- Status -->
    <div class="mb-3">
      <label class="form-label">Trạng thái</label><br>
      <input type="radio" name="status" value="1" checked> Hoạt động
      <input type="radio" name="status" value="0"> Khóa
    </div>

    <button type="submit" class="btn btn-success">💾 Lưu</button>
    <a href="<c:url value='/${rolePath}/videos'/>" class="btn btn-secondary">⏪ Quay lại danh sách</a>
  </form>
</div>
</body>
</html>

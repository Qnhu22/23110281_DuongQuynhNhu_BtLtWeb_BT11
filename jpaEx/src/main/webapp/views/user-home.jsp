<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Trang User</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
  <div class="container">
    <a class="navbar-brand fw-bold" href="#">User Home</a>
    <div>
      <a class="btn btn-light" href="<c:url value='/logout'/>">Đăng xuất</a>
    </div>
  </div>
</nav>

<div class="container mt-4">
  <h2 class="mb-4 text-primary">Danh sách Category</h2>
  <a class="btn btn-primary mb-3" href="<c:url value='/user/category/add'/>">+ Thêm Category</a>

  <table class="table table-bordered table-striped shadow-sm">
    <thead class="table-primary">
      <tr>
        <th>STT</th>
        <th>Hình ảnh</th>
        <th>Tên Category</th>
        <th>Trạng thái</th>
        <th>Hành động</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${listcate}" var="cate" varStatus="stt">
        <tr>
          <td>${stt.index + 1}</td>
          <td>
            <c:if test="${not empty cate.images}">
              <img src="<c:url value='/image?fname=${cate.images}'/>" width="80" class="img-thumbnail"/>
            </c:if>
          </td>
          <td>${cate.categoryname}</td>
          <td>
            <span class="badge ${cate.status == 1 ? 'bg-success' : 'bg-secondary'}">
              <c:choose>
                <c:when test="${cate.status == 1}">Đang hoạt động</c:when>
                <c:otherwise>Khóa</c:otherwise>
              </c:choose>
            </span>
          </td>
          <td>
            <c:if test="${cate.user.userId == sessionScope.user.userId}">
              <a class="btn btn-sm btn-warning" href="<c:url value='/user/category/edit?id=${cate.categoryId}'/>">Sửa</a>
              <a class="btn btn-sm btn-danger" href="<c:url value='/user/category/delete?id=${cate.categoryId}'/>"
                 onclick="return confirm('Bạn có chắc muốn xóa?')">Xóa</a>
            </c:if>
          </td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</div>
</body>
</html>

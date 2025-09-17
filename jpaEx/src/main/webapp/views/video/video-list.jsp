<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Danh sách Video</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
  <h2 class="mb-4 text-primary">Danh sách Video</h2>
<!-- Form tìm kiếm -->
<form class="d-flex mb-3" method="get" action="<c:url value='/${rolePath}/videos'/>">
  <input class="form-control me-2" type="text" name="keyword"
         placeholder="Nhập tiêu đề hoặc mô tả để tìm..."
         value="${param.keyword}">
  <button class="btn btn-outline-primary me-2" type="submit">🔍 Tìm kiếm</button>
  <a class="btn btn-outline-secondary" href="<c:url value='/${rolePath}/videos'/>">📋 Reset</a>
</form>


  <!-- Nút thêm video -->
  <a class="btn btn-success mb-3" href="<c:url value='/${rolePath}/video/add'/>">+ Thêm Video</a>
  <!-- Nút quay lại category -->
  <a class="btn btn-secondary mb-3" href="<c:url value='/${rolePath}/categories'/>">⏪ Quay lại Category</a>

  <table class="table table-bordered table-striped shadow-sm">
    <thead class="table-primary">
      <tr>
        <th>STT</th>
        <th>Hình ảnh</th>
        <th>Tiêu đề</th>
        <th>Mô tả</th>
        <th>Lượt xem</th>
        <th>Danh mục</th>
        <th>Trạng thái</th>
        <th>Hành động</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${listvideo}" var="vid" varStatus="stt">
        <tr>
          <td>${stt.index + 1}</td>
          <td>
            <c:if test="${not empty vid.images}">
              <img src="<c:url value='/image?fname=${vid.images}'/>" width="80" class="img-thumbnail"/>
            </c:if>
          </td>
          <td>${vid.title}</td>
          <td>${vid.description}</td>
          <td>${vid.views}</td>
          <td>${vid.category.categoryname}</td>
          <td>
            <span class="badge ${vid.status == 1 ? 'bg-success' : 'bg-secondary'}">
              <c:choose>
                <c:when test="${vid.status == 1}">Hoạt động</c:when>
                <c:otherwise>Khóa</c:otherwise>
              </c:choose>
            </span>
          </td>
          <td>
            <c:if test="${sessionScope.user.roleId == 1 
                         || sessionScope.user.roleId == 2 
                         || (sessionScope.user.roleId == 3 && vid.user.userId == sessionScope.user.userId)}">
              <a class="btn btn-sm btn-warning" href="<c:url value='/${rolePath}/video/edit?id=${vid.videoId}'/>">Sửa</a>
              <a class="btn btn-sm btn-danger" href="<c:url value='/${rolePath}/video/delete?id=${vid.videoId}'/>"
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

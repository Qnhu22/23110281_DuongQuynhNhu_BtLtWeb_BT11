<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sửa Video</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
<div class="container mt-4">
    <h2 class="text-warning">Sửa Video</h2>

    <form action="<c:url value='/${rolePath}/video/update'/>" method="post" enctype="multipart/form-data" class="mt-3">

        <input type="hidden" name="videoId" value="${video.videoId}"/>

        <!-- Title -->
        <div class="mb-3">
            <label class="form-label">Tiêu đề</label>
            <input type="text" name="title" class="form-control" value="${video.title}" required/>
        </div>

        <!-- Description -->
        <div class="mb-3">
            <label class="form-label">Mô tả</label>
            <textarea name="description" class="form-control">${video.description}</textarea>
        </div>

        <!-- Category -->
        <div class="mb-3">
            <label class="form-label">Danh mục</label>
            <select name="categoryId" class="form-select" required>
                <c:forEach items="${listcate}" var="cate">
                    <option value="${cate.categoryId}"
                        <c:if test="${video.category.categoryId == cate.categoryId}">selected</c:if>>
                        ${cate.categoryname}
                    </option>
                </c:forEach>
            </select>
        </div>

        <!-- Current Image -->
        <div class="mb-3">
            <label class="form-label">Ảnh hiện tại</label><br/>
            <c:if test="${not empty video.images}">
                <img src="<c:url value='/image?fname=${video.images}'/>" width="120" class="img-thumbnail mb-2"/>
            </c:if>
            <input type="file" name="poster" class="form-control"/>
        </div>

        <!-- Views -->
        <div class="mb-3">
            <label class="form-label">Số lượt xem</label>
            <input type="number" name="views" class="form-control" value="${video.views}" min="0">
        </div>

        <!-- Status -->
        <div class="mb-3">
            <label class="form-label">Trạng thái</label><br>
            <input type="radio" name="status" value="1" <c:if test="${video.status == 1}">checked</c:if>> Hoạt động
            <input type="radio" name="status" value="0" <c:if test="${video.status == 0}">checked</c:if>> Khóa
        </div>

        <button type="submit" class="btn btn-warning">🔄 Cập nhật</button>
        <a href="<c:url value='/${rolePath}/videos'/>" class="btn btn-secondary">⏪ Quay lại danh sách</a>
    </form>
</div>
</body>
</html>

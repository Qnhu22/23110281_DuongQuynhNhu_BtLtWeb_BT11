<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<ul class="nav flex-column">
  <c:choose>
    <c:when test="${not empty sessionScope.user}">
      <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/profile">Thông tin cá nhân</a>
      </li>
      <li class="nav-item">
        <a class="nav-link text-danger" href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
      </li>
    </c:when>
    <c:otherwise>
      <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/login">Đăng nhập</a>
      </li>
    </c:otherwise>
  </c:choose>
</ul>
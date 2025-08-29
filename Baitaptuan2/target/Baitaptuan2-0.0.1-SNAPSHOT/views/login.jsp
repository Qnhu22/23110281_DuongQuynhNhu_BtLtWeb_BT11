<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>
</head>
<body>
    <form action="login" method="post">
        <h2>Đăng nhập</h2>
        <c:if test="${alert != null}">
            <h3 class="alert alertdanger">${alert}</h3>
        </c:if>
        <section>
            <label class="input login-input">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                    <input type="text" placeholder="Tài khoản" name="username" class="form-control" required>
                </div>
            </label>
        </section>
        <section>
            <label class="input login-input">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                    <input type="password" placeholder="Mật khẩu" name="password" class="form-control" required>
                </div>
            </label>
        </section>
        <section>
            <label>
                <input type="checkbox" name="remember" value="on"> Nhớ mật khẩu
            </label>
        </section>
        <button type="submit">Đăng nhập</button>
    </form>
    <section>
        <a href="${pageContext.request.contextPath}/forget-password">Quên mật khẩu?</a>
        <br>
        <a href="${pageContext.request.contextPath}/register">Đăng ký</a>
    </section>
</body>
</html>
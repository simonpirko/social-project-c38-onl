<%--
  Created by IntelliJ IDEA.
  User: Андрей
  Date: 20.02.2026
  Time: 13:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Registratoin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <div class="row justify-content-center">
        <c:if test="${message!=null}">
            <c:choose>
                <c:when test="${isCreated}">
                    <div class="alert alert-success" role="alert">
                        ${message}
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="alert alert-warning" role="alert">
                        ${message}
                    </div>
                </c:otherwise>
            </c:choose>
        </c:if>
        <div class="col-3">
            <form action="/registration" method="post">
                <div class="mb-3">
                    <label for="exampleInputNickname" class="form-label">Nickname</label>
                    <input type="text" name="nickname" class="form-control" id="exampleInputNickname" placeholder="Enter your nickname">
                </div>
                <div class="mb-3">
                    <label for="exampleInputEmail1" class="form-label">Email</label>
                    <input type="email" name="email" class="form-control" id="exampleInputEmail1"
                           aria-describedby="emailHelp" placeholder="Enter your email">
                </div>
                <div class="mb-3">
                    <label for="exampleInputPassword" class="form-label">Password</label>
                    <input type="password" name="password" class="form-control" id="exampleInputPassword" placeholder="Enter your password">
                </div>
                <div class="mb-3">
                    <label for="exampleInputRepeatPassword" class="form-label">Repeat password</label>
                    <input type="password" name="repeatPassword" class="form-control" id="exampleInputRepeatPassword" placeholder="Repeat your nickname">
                </div>
                <button type="submit" class="btn btn-primary w-100">Registration</button>
                <div class="text-center mt-3">
                    <span>Already have an account?</span>
                    <a href="/login" class="text-decoration-none fw-semibold">
                        Sign in
                    </a>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>

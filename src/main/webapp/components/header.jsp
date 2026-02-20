<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<body>
<nav class="sticky-top navbar navbar-expand-lg bg-dark navbar-dark border-bottom border-body">
  <div class="container">
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <a class="navbar-brand" href="/">
      <i class="bi bi-camera"></i>
    </a>
    <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link" href="/posts">Posts</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/users">Users</a>
        </li>
      </ul>
      <div class="d-flex gap-2">
        <c:if test="${sessionScope.account == null}">
          <a href="/login" class="btn btn-outline-light d-flex gap-1 align-items-center">
            Login
          </a>
          <a href="/register" class="btn btn-outline-light d-flex gap-1 align-items-center">
            Register
          </a>
        </c:if>
        <c:if test="${sessionScope.account != null}">
          <a href="/post/create" class="btn btn-outline-success d-flex gap-1 align-items-center">
            <i class="bi bi-plus-circle-dotted"></i>
            Create post
          </a>
          <a href="/profile" class="btn btn-outline-light d-flex gap-1 align-items-center">
            <i class="bi bi-person-square"></i>
            Profile
          </a>
          <form method="post" action="/logout">
            <button class="btn btn-outline-light d-flex gap-1 align-items-center">
              <i class="bi bi-box-arrow-right"></i>
              Logout
            </button>
          </form>
        </c:if>
      </div>
    </div>
  </div>
</nav>
</body>
</html>
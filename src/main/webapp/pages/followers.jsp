<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Подписчики</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
</head>
<body class="min-vh-100 d-flex flex-column bg-light">
<jsp:include page="../components/header.jsp"/>

<div class="container col-12 col-md-8 col-lg-6 flex-grow-1 py-5">
  <div class="d-flex align-items-center mb-4">
    <a href="javascript:history.back()" class="btn btn-link text-dark p-0 me-3">
      <i class="bi bi-arrow-left fs-3"></i>
    </a>
    <h2 class="mb-0">Подписчики</h2>
  </div>

  <div class="card shadow-sm border-0 rounded-4">
    <div class="card-body p-0">
      <c:choose>
        <c:when test="${not empty followers}">
          <ul class="list-group list-group-flush rounded-4">
            <c:forEach var="follower" items="${followers}">
              <li class="list-group-item d-flex align-items-center justify-content-between p-3">
                <div class="d-flex align-items-center">
                  <div class="bg-secondary text-white rounded-circle d-flex align-items-center justify-content-center me-3"
                       style="width:50px; height:50px; font-size:1.2rem;">
                      ${follower.nickname}
                  </div>
                  <div>
                    ${follower.nickname}
                  </div>
                </div>
                <div>
                  <a href="/account?id=${follower.id}" class="btn btn-sm btn-outline-primary rounded-pill px-3">
                    Профиль
                  </a>
                </div>
              </li>
            </c:forEach>
          </ul>
        </c:when>

        <c:otherwise>
          <div class="text-center py-5">
            <i class="bi bi-people text-muted" style="font-size: 4rem;"></i>
            <p class="mt-3 text-secondary fs-5">Пока нет подписчиков</p>
          </div>
        </c:otherwise>
      </c:choose>
    </div>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

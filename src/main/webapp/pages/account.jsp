<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Account</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
</head>
<body class="min-vh-100 d-flex flex-column">
<jsp:include page="../components/header.jsp"/>
<div class="py-5 container col-12 flex-grow-1">
    <div class="row mb-4">
        <div class="col-auto pe-0">
            <div class="bg-light text-dark rounded-circle d-flex align-items-center justify-content-center"
                 style="width:80px;height:80px;font-size:2rem;">
                U
            </div>
        </div>
        <div class="col ps-3">
            <h2>${accountData.account().nickname}</h2>
            <div class="text-muted mb-2">
                ${accountData.postsCount()} постов
                    <a href="/account/followers?id=${accountData.account().getId()}" class="text-decoration-none text-muted me-2">
                        ${accountData.followersCount()} подписчиков
                    </a>
                    <a href="/account/following?id=${accountData.account().getId()}" class="text-decoration-none text-muted">
                        ${accountData.followingCount()} подписок
                    </a>
            </div>
            <c:if test="${!isPersonal}">
                <c:choose>
                    <c:when test="${isFollowing}">
                        <form action="/account/subscribe?id=${accountData.account().getId()}" method="post" style="display: inline;">
                            <input type="hidden" name="userId" value="${accountData.account().id}">
                            <button type="submit" class="btn btn-outline-primary">Отписаться</button>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <form action="/account/subscribe?id=${accountData.account().getId()}" method="post" style="display: inline;">
                            <input type="hidden" name="userId" value="${accountData.account().id}">
                            <button type="submit" class="btn btn-primary">Подписаться</button>
                        </form>
                    </c:otherwise>
                </c:choose>
            </c:if>
        </div>
    </div>

    <div class="mt-1 row row-cols-1 row-cols-md-2 row-cols-lg-4 g-4">
        <c:forEach var="currentPost" items="${accountData.userPosts()}">
            <div class="col">
                <jsp:include page="../components/post.jsp">
                    <jsp:param name="postId" value="${currentPost.id}"/>
                </jsp:include>
            </div>
        </c:forEach>

        <c:if test="${empty accountData.userPosts()}">
            <div class="col-12 text-center py-5">
                <p class="text-muted">У пользователя пока нет публикаций</p>
            </div>
        </c:if>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

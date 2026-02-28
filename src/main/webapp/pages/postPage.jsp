<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Пост</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
</head>
<body class="min-vh-100 d-flex flex-column bg-light">
<jsp:include page="../components/header.jsp"/>

<div class="container col-12 col-sm-10 col-md-8 col-lg-6 col-xl-5 flex-grow-1 d-flex flex-column justify-content-center py-5">
    <c:choose>
        <c:when test="${empty post}">
            <div class="alert alert-warning text-center mb-4">
                <i class="bi bi-exclamation-triangle fs-1 mb-3"></i>
                <h4>Пост не найден</h4>
                <p>Пост с указанным ID не существует в базе данных</p>
                <a href="/" class="btn btn-primary">← На главную</a>
            </div>
        </c:when>

        <c:otherwise>
            <div class="card mb-4 shadow-sm">
                <c:choose>
                    <c:when test="${not empty post.images}">
                        <c:set var="imageUrls" value="${fn:split(post.images, ',')}"/>
                        <div class="row g-2 mb-3 p-3 bg-light">
                            <c:forEach var="imageUrl" items="${imageUrls}" varStatus="loop">
                                <div class="col-6 col-sm-4 col-md-2 col-lg">
                                    <img src="${fn:trim(imageUrl)}"
                                         class="img-fluid rounded w-100 shadow-sm"
                                         alt="Изображение поста ${loop.index + 1}"
                                         style="aspect-ratio: 4/3; object-fit: cover; height: 180px;"
                                         onerror="this.src='https://via.placeholder.com/300x200/eee?text=Изображение'">
                                </div>
                            </c:forEach>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="bg-light text-center py-5 rounded-top">
                            <i class="bi bi-image fs-1 text-muted mb-2"></i>
                            <p class="text-muted mb-0">Изображения отсутствуют</p>
                        </div>
                    </c:otherwise>
                </c:choose>

                <div class="card-body">
                    <div class="row row-gap-1 mb-3 align-items-center">
                        <div class="col-auto">
                            <i class="fs-2 bi bi-person-circle text-primary"></i>
                        </div>
                        <div class="col">
                            <h5 class="mb-1 fw-bold">${fn:escapeXml(post.title)}</h5>
                            <div class="d-flex align-items-center gap-2">
                                <span class="text-secondary">Автор ID: ${post.accountID}</span>
                                <span class="badge bg-light text-dark border">${post.createAt}</span>
                                <button class="btn btn-outline-primary btn-sm">Подписаться</button>
                            </div>
                        </div>
                    </div>

                    <p class="card-text text-dark lh-lg">${fn:escapeXml(post.description)}</p>

                    <div class="row align-items-center mt-4 pt-3 border-top">
                        <form method="post" action="/post/like" class="col-auto">
                            <button class="btn text-danger p-0" name="postId" value="${post.id}">
                                <i class="bi bi-heart${post.id eq 1 ? '-fill' : ''} fs-4"></i>
                            </button>
                        </form>
                        <span class="text-secondary ms-2">12</span>

                        <div class="col text-secondary d-flex align-items-center ms-4">
                            <i class="bi bi-chat-dots fs-5 me-1"></i>
                            <span>5 комментариев</span>
                        </div>

                        <div class="col text-end">
                            <span class="text-muted small">${post.id}</span>
                        </div>
                    </div>
                </div>
            </div>

            <div class="border rounded-4 p-4 bg-white shadow-sm">
                <h5 class="mb-3 fw-semibold">
                    <i class="bi bi-chat-dots me-2"></i>Комментарии
                </h5>

                <form class="border rounded-3 p-3 mb-3 bg-light">
                    <div class="row">
                        <div class="col-10">
                            <textarea class="form-control" rows="2"
                                      placeholder="Напишите комментарий..."></textarea>
                        </div>
                        <div class="col-2 d-flex align-items-end">
                            <button class="btn btn-primary w-100">Отправить</button>
                        </div>
                    </div>
                </form>

                <div class="text-center py-4">
                    <i class="bi bi-chat-square-text fs-3 text-muted mb-2"></i>
                    <p class="text-secondary mb-0">Комментарии отсутствуют</p>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>

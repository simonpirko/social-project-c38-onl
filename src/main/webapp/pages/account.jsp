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
            <h2>Nickname</h2>
            <div class="text-muted mb-2">
                0 постов • 0 подписчиков • 0 подписок
            </div>
            <a href="/profile/edit" class="btn btn-outline-primary">Подписаться</a>
        </div>
    </div>

    <div class="mt-1 row row-cols-1 row-cols-md-2 row-cols-lg-4 g-4">
        <div class="col">
            <jsp:include page="../components/post.jsp"/>
        </div>
        <div class="col">
            <jsp:include page="../components/post.jsp"/>
        </div>
        <div class="col">
            <jsp:include page="../components/post.jsp"/>
        </div>
        <div class="col">
            <jsp:include page="../components/post.jsp"/>
        </div>
        <div class="col">
            <jsp:include page="../components/post.jsp"/>
        </div>
        <div class="col">
            <jsp:include page="../components/post.jsp"/>
        </div>
        <div class="col">
            <jsp:include page="../components/post.jsp"/>
        </div>
        <div class="col">
            <jsp:include page="../components/post.jsp"/>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

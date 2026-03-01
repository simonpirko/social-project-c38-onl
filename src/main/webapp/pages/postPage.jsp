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

<div class="container col-12 col-sm-10 col-md-8 flex-grow-1 d-flex flex-column justify-content-center py-5">
    <c:choose>
        <c:when test="${empty post}">
            <div class="alert alert-warning text-center mb-4">
                <i class="bi bi-exclamation-triangle fs-1 mb-3"></i>
                <h4>Post not found</h4>
                <p>The post with the specified ID does not exist in the database.</p>
                <a href="/" class="btn btn-primary">← Home</a>
            </div>
        </c:when>

        <c:otherwise>
            <div class="card mb-4 shadow-sm">
                <c:if test="${not empty post.images}">
                    <c:set var="imageUrls" value="${fn:split(post.images, '|')}"/>

                    <div class="mb-3">
                        <img src="${fn:trim(imageUrls[0])}"
                             class="img-fluid rounded w-100 shadow-sm"
                             alt="Main image"
                             style="aspect-ratio: 16/9; object-fit: cover; width: 100%; max-height: 400px;"
                        />
                    </div>

                    <c:if test="${fn:length(imageUrls) > 1}">
                        <div class="row g-2 mb-3 p-3">
                            <c:forEach var="imageUrl" items="${imageUrls}" varStatus="loop" begin="1">
                                <div class="col-3">
                                    <img src="${fn:trim(imageUrl)}"
                                         class="img-fluid rounded w-100 shadow-sm"
                                         alt="Image ${loop.index + 1}"
                                         style="aspect-ratio: 4/3; object-fit: cover; height: 180px;"
                                    />
                                </div>
                            </c:forEach>
                        </div>
                    </c:if>
                </c:if>

                <div class="card-body">
                    <div class="row row-gap-1 mb-3 align-items-center">
                        <div class="col-auto">
                            <i class="fs-2 bi bi-person-circle"></i>
                        </div>
                        <div class="col">
                            <h5 class="mb-1 fw-bold">${fn:escapeXml(post.title)}</h5>
                            <div class="d-flex align-items-center gap-2">
                                <span class="text-secondary">Author ID: ${post.accountID}</span>
                                <span class="badge bg-light text-dark border">${post.createAt}</span>
                                <button class="btn btn-outline-primary btn-sm">Subsribe</button>
                            </div>
                        </div>
                    </div>

                    <p class="card-text text-dark lh-lg">${fn:escapeXml(post.description)}</p>

                    <div class="row align-items-center mt-2">
                        <form method="post" action="/post/like" class="w-auto row align-items-center mb-0" style="position: relative;">
                            <button class="btn w-auto text-danger"><i class="bi bi-heart-fill fs-5"></i></button>
                            <span class="ps-0 w-auto text-secondary">12</span>
                        </form>
                        <div class="row text-secondary w-auto align-items-center">
                            <i class="w-auto bi bi-chat-fill fs-5"></i>
                            <span class="ps-0 w-auto">12</span>
                        </div>
                    </div>
                </div>
            </div>

            <div class="border rounded-4 p-4 bg-white shadow-sm">
                <h5 class="mb-3 fw-semibold">
                    <i class="bi bi-chat-dots me-2"></i> Comments
                </h5>

                <form action="/posts/comments/add/${post.id}" method="post" class="border rounded-3 p-3 mb-3 bg-light">
                    <div class="row">
                        <div class="col-10">
                            <textarea name="text" class="form-control" rows="2"
                                      placeholder="Enter the comment..."></textarea>
                        </div>
                        <div class="col-2 d-flex align-items-end">
                            <button class="btn btn-primary w-100">Send</button>
                        </div>
                    </div>
                    <c:if test="${not empty commentMessage}">
                        <p style="color: red;">${commentMessage}</p>
                    </c:if>
                </form>


                <div class="text-center py-4">
                    <i class="bi bi-chat-square-text fs-3 text-muted mb-2"></i>
                    <p class="text-secondary mb-0">No comments</p>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<html>
<body>
<div class="card">
    <img src="${fn:split(post.images, '|')[0]}" class="card-img-top" alt="${post.title}" style="aspect-ratio: 4/3; object-fit: cover; height: 180px;">
    <div class="card-body">
        <div class="row align-items-center">
            <div class="w-auto">
                <i class="fs-3 bi bi-person-circle"></i>
            </div>
            <div class="w-auto">
                <p class="mb-0">${post.account.nickname}</p>
                <span class="text-secondary fs-6">${post.createAt}</span>
            </div>
        </div>
        <h5 class="card-title mt-3">${post.title}</h5>
        <div class="d-flex row-gap-2">
            <p class="mb-0 card-text text-truncate text-dark pe-2">${post.description}</p>
            <a href="/posts/${post.id}" class="flex-shrink-0 w-auto link-opacity-100">Read more</a>
        </div>
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
</body>
</html>
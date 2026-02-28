<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<body>
<div class="card">
    <img src="${post.images}" class="card-img-top" alt="${post.title}">
    <div class="card-body">
        <div class="row row-gap-1">
            <div class="w-auto">
                <i class="fs-2 bi bi-person-circle"></i>
            </div>
            <div class="w-auto">
                <p class="mb-0">${post.account.nickname}</p>
                <span class="text-secondary">${post.createAt}</span>
            </div>
        </div>
        <h5 class="card-title mt-3">${post.title}</h5>
        <div class="d-flex row-gap-2">
            <p class="mb-0 card-text text-truncate text-dark pe-2">${post.description}</p>
            <a href="/post" class="flex-shrink-0 w-auto link-opacity-100">Read more</a>
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
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<body>
<div class="card">
    <img src="https://treesforall.nl/app/uploads/2022/03/Bos-Nederland-Epe-e1719389547661-0x1400-c-default.webp" class="card-img-top" alt="title">
    <div class="card-body">
        <div class="row row-gap-1">
            <div class="w-auto">
                <i class="fs-2 bi bi-person-circle"></i>
            </div>
            <div class="w-auto">
                <p class="mb-0">Петров Иван</p>
                <span class="text-secondary">11.11.2011</span>
            </div>
        </div>
        <h5 class="card-title mt-3">We have created the post card</h5>
        <div class="d-flex row-gap-2">
            <p class="mb-0 card-text text-truncate text-dark pe-2">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
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
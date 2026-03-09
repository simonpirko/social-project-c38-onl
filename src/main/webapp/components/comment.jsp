<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="border rounded-3 p-3 mb-3 bg-white">
    <div class="d-flex align-items-center mb-2">

        <a href="/account/${comment.account.id}" class="rounded-circle border border-secondary flex-shrink-0 me-3" style="width: 45px; height: 45px;">
        </a>

        <div class="d-flex flex-column lh-sm">
            <span class="fw-bold text-dark" style="font-size: 0.95rem;">${comment.account.nickname}</span>
            <span class="text-secondary mt-1" style="font-size: 0.75rem;">${comment.createdAt}</span>
        </div>

    </div>

    <div class="text-secondary" style="font-size: 0.95rem; line-height: 1.4;">
        ${comment.text}
    </div>
</div>
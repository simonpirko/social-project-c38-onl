<%--
  Created by IntelliJ IDEA.
  User: Андрей
  Date: 20.02.2026
  Time: 13:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Registration</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css"
          integrity="sha384-tViUnnbYAV00FLIhhi3v/dWt3Jxw4gZQcNoSCxCIFNJVCx7/D55/wXsrNIRANwdD" crossorigin="anonymous">
</head>
<body class="min-vh-100 d-flex flex-column">
<jsp:include page="../components/header.jsp"/>
<div class="py-5 container col-12 flex-grow-1 d-flex flex-column justify-content-center">
    <h1 class="h1">Posts</h1>

    <form method="get" action="/" class="mt-3 mb-2">
        <div class="input-group ">
            <span class="input-group-text" id="inputGroup-sizing-default"><i class="bi bi-search"></i></span>
            <input type="text" name="search" class="form-control" aria-label="Sizing example input"
                   aria-describedby="inputGroup-sizing-default" placeholder="Search">
            <button type="submit" class="btn btn-outline-primary">Search</button>
        </div>
    </form>

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
</body>
</html>

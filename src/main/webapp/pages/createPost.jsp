<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Create a post</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2 class="mb-4 text-center">Create a post</h2>
    <form action="/post/create" method="post" class="border p-4 rounded bg-light">
        <div class="mb-3">
            <label for="title" class="form-label">Title</label>
            <input type="text" class="form-control" id="title" name="title" placeholder="Enter post title" required>
        </div>
        <div class="mb-3">
            <label for="description" class="form-label">Description</label>
            <textarea class="form-control" id="description" name="description" rows="4" required></textarea>
        </div>
        <div class="mb3">
            <label for="img1" class="form-label">Link to the image 1</label>
            <input type="url" class="form-control" id="img1" name="images" placeholder="http://example.com/image1.jsp" required>
        </div>
        <div class="mb-3">
            <label for="img2" class="form-label">Link to the image 2</label>
            <input type="url" class="form-control" id="img2" name="images" placeholder="http://example.com/image2.jsp">
        </div>
        <div class="mb-3">
            <label for="img3" class="form-label">Link to the image 3</label>
            <input type="url" class="form-control" id="img3" name="images" placeholder="http://example.com/image3.jsp">
        </div>
        <div class="mb-3">
            <label for="img4" class="form-label">Link to the image 4</label>
            <input type="url" class="form-control" id="img4" name="images" placeholder="http://example.com/image4.jsp">
        </div>
        <div class="mb-3">
            <label for="img5" class="form-label">Link to the image 5</label>
            <input type="url" class="form-control" id="img5" name="images" placeholder="http://example.com/image5.jsp">
        </div>

        <button type="submit" class="btn btn-primary w-100">Create</button>
    </form>
</div>
</body>
</html>
package by.tms.socialprojectc38onl.web.servlet;


import by.tms.socialprojectc38onl.models.Post;
import by.tms.socialprojectc38onl.service.PostService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/")
public class PostsServlet extends HttpServlet {
    private final PostService postsService = PostService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String findParam = req.getParameter("search");
        List<Post> posts = postsService.findByTitle(findParam);

        req.setAttribute("posts", posts);
        req.setAttribute("find", findParam);

        req.setAttribute("posts", posts);
        getServletContext().getRequestDispatcher("/pages/index.jsp").forward(req, resp);
    }
}
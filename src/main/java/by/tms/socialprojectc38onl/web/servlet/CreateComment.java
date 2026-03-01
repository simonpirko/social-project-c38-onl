package by.tms.socialprojectc38onl.web.servlet;


import by.tms.socialprojectc38onl.dao.DAOPosts;
import by.tms.socialprojectc38onl.models.Account;
import by.tms.socialprojectc38onl.models.Post;
import by.tms.socialprojectc38onl.service.CommentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@WebServlet("/posts/comments/add/*")
public class CreateComment extends HttpServlet {
    private DAOPosts postsService = DAOPosts.getInstance();
    CommentService commentService = CommentService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        int postId = 0;

        try {

            if (Objects.nonNull(pathInfo) && pathInfo.matches("/\\d+")) {
                postId = Integer.parseInt(pathInfo.substring(1));
            }

            if (postId == 0) {
                resp.sendRedirect("/posts/" + postId);
                return;
            }
            commentService.createComment(
                    req.getParameter("text"),
                    Optional.of((Account) req.getSession().getAttribute("account")),
                    postId
            );

            resp.sendRedirect("/posts/" + postId);

        } catch (RuntimeException e) {
            req.setAttribute("commentMessage", e.getMessage());

            Post post = postsService.findById(postId).orElse(null);
            req.setAttribute("post", post);

            req.getRequestDispatcher("/pages/postPage.jsp").forward(req, resp);
        }
    }

}

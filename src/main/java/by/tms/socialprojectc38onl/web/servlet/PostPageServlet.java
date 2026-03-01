package by.tms.socialprojectc38onl.web.servlet;

import by.tms.socialprojectc38onl.dao.DAOPosts;
import by.tms.socialprojectc38onl.models.Post;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@WebServlet("/posts/*")
public class PostPageServlet extends HttpServlet {

    private DAOPosts postsService = DAOPosts.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        int postId = 0;

        if (Objects.nonNull(pathInfo) && pathInfo.matches("/\\d+")) {
            postId = Integer.parseInt(pathInfo.substring(1));
        }

        Optional<Post> postOptional = postsService.findById(postId);

        if (postOptional.isEmpty()) {
            resp.setStatus(404);
            req.getRequestDispatcher("/pages/postPage.jsp").forward(req, resp);
            return;
        }

        req.setAttribute("post", postOptional.get());
        req.getRequestDispatcher("/pages/postPage.jsp").forward(req, resp);
    }
}

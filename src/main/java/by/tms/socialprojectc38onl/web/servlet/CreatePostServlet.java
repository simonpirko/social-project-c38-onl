package by.tms.socialprojectc38onl.web.servlet;

import by.tms.socialprojectc38onl.exception.CreatePostException;
import by.tms.socialprojectc38onl.models.Account;
import by.tms.socialprojectc38onl.service.CreatePostService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/posts/create")
public class CreatePostServlet extends HttpServlet {
    final CreatePostService create = new CreatePostService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String description = req.getParameter("description");

        if (title != null && description != null) {
            resp.setContentType("text/plain;charset=UTF-8");
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("Post created successfully!");
        } else {
            resp.setContentType("text/html;charset=UTF-8");
            req.getRequestDispatcher("/pages/createPost.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            create.createPost(req.getParameter("title"),
                    req.getParameter("description"),
                    List.of(req.getParameter("img1"),
                            req.getParameter("img2"),
                            req.getParameter("img3"),
                            req.getParameter("img4"),
                            req.getParameter("img5")),
                    Optional.of((Account) req.getSession().getAttribute("account")));

            req.getRequestDispatcher("/components/post.jsp").forward(req, resp);

        } catch (CreatePostException e) {
            req.setAttribute("message", e.getMessage());
            req.setAttribute("isCreated", false);
            req.getRequestDispatcher("/pages/createPost.jsp").forward(req, resp);
        }
    }
}

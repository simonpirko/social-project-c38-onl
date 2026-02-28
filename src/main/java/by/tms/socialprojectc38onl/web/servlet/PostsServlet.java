package by.tms.socialprojectc38onl.web.servlet;



import by.tms.socialprojectc38onl.dao.DAOPosts;
import by.tms.socialprojectc38onl.models.Post;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/")
public class PostsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Post> posts;
        DAOPosts daoPosts = DAOPosts.getInstance();
        posts = daoPosts.findAll();
        req.setAttribute("posts",posts);
        req.getRequestDispatcher("/pages/index.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String find = req.getParameter("search");
        if(find==null){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        else{
           List<Post> posts;
           DAOPosts daoPosts = DAOPosts.getInstance();
           posts = daoPosts.findByTitle(find);
           if (posts==null || posts.isEmpty()){
               req.setAttribute("find",find);
               getServletContext().getRequestDispatcher("/pages/postsNotFound.jsp").forward(req, resp);
           }
           else {
            req.setAttribute("posts",posts);
            getServletContext().getRequestDispatcher("/pages/index.jsp").forward(req, resp);}
        }
    }
}
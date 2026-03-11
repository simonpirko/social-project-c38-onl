package by.tms.socialprojectc38onl.service;

import by.tms.socialprojectc38onl.dao.DAOPosts;
import by.tms.socialprojectc38onl.exception.CreatePostException;
import by.tms.socialprojectc38onl.models.Account;
import by.tms.socialprojectc38onl.models.Post;
import jakarta.servlet.http.HttpSession;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class PostService {
    private final DAOPosts postDAO;
    private static PostService INSTANCE;

    private PostService() { this.postDAO = DAOPosts.getInstance(); }

    public static synchronized PostService getInstance() {
        if (Objects.isNull(INSTANCE)) {
            INSTANCE = new PostService();
        }
        return INSTANCE;
    }

    public List<Post> findAll(int accountID) {
        return postDAO.findAll(accountID);
    }

    public Optional<Post> findById(Integer id) {
        if (Objects.isNull(id) || id <= 0) {
            return Optional.empty();
        }
        return postDAO.findById(id);
    }

    public List<Post> findByTitle(String title, int accountID) {
        if (Objects.isNull(title) || title.isBlank()) {
            return postDAO.findAll(accountID);
        }
        return postDAO.findByTitle(title, accountID);
    }

    public String createPost(String title, String description, List<String> img, Optional<Account> user) {

        if (user.isEmpty()) {
            throw new CreatePostException("You are not authorized for this action");
        }

        if (title.isEmpty() || description.isEmpty() || img.isEmpty()) {
            throw new CreatePostException("Fill in the title, description and upload at least one image");
        }

        StringBuilder images = new StringBuilder();

        for (String url : img) {

            if (url.isEmpty()) {
                continue;
            }

            if (images.isEmpty()) {
                images.append(url);
                continue;
            }

            images.append("|").append(url);
        }

        Post post = new Post();
        post.setTitle(title);
        post.setDescription(description);
        post.setAccountID(user.get().getId());
        post.setImages(images.toString());

        return postDAO.save(post);
    }

    public boolean isYorLike(HttpSession session){

        if (session != null)
        {
            Account account = (Account) session.getAttribute("account");
            int id = account.getId();

        }

        else return false;
        return false;
    }
}

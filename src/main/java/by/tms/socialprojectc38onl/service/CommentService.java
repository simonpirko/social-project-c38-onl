package by.tms.socialprojectc38onl.service;

import by.tms.socialprojectc38onl.dao.CommentsDAO;
import by.tms.socialprojectc38onl.exception.AuthException;
import by.tms.socialprojectc38onl.exception.CreateCommentException;
import by.tms.socialprojectc38onl.models.Account;
import by.tms.socialprojectc38onl.models.Comment;

import java.util.Optional;

public class CommentService {
    private static CommentService INSTANCE;
    CommentsDAO commentsDAO = CommentsDAO.getInstance();


    private CommentService() {
    }

    public static CommentService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CommentService();
        }

        return INSTANCE;
    }


    public void createComment(String text, Optional<Account> account, int postId) {
        if (text.isEmpty()) {
            throw new CreateCommentException("The content of comment is empty");
        }

        if (account.isEmpty()) {
            throw new AuthException("User is not authorized");
        }

        Comment comment = new Comment();
        comment.setText(text);
        comment.setPostId(postId);
        comment.setAccountId(account.get().getId());

        commentsDAO.save(comment);
    }
}

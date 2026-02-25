package by.tms.socialprojectc38onl.dao;

import by.tms.socialprojectc38onl.PgConnection;
import by.tms.socialprojectc38onl.models.Comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommentsDAO {
    private static CommentsDAO INSTANCE;

    private CommentsDAO() {
    }

    public static CommentsDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CommentsDAO();
        }

        return INSTANCE;
    }

    public void save(Comment comment) {
        try (Connection connection = PgConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("INSERT INTO post_comments (account_id,post_id,text,created_at) VALUES(?,?,?,?)");
            preparedStatement.setLong(1, comment.getAccountId());
            preparedStatement.setLong(2, comment.getPostId());
            preparedStatement.setString(3, comment.getText());
            preparedStatement.setTimestamp(4, comment.getCreatedAt());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Comment> findById(Long id) {
        try (Connection connection = PgConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("SELECT * FROM post_comments WHERE id = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Comment comment = new Comment();
                comment.setId(resultSet.getLong("id"));
                comment.setAccountId(resultSet.getLong("account_id"));
                comment.setPostId(resultSet.getLong("post_id"));
                comment.setText(resultSet.getString("text"));
                comment.setCreatedAt(resultSet.getTimestamp("created_at"));

                return Optional.of(comment);
            }
            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Comment> findByPostId(Long postId) {
        List<Comment> comments = new ArrayList<>();
        try (Connection connection = PgConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("SELECT id, account_id, post_id, text, created_at " +
                            "FROM post_comments WHERE post_id = ?");
            preparedStatement.setLong(1, postId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Comment comment = new Comment();
                comment.setId(resultSet.getLong("id"));
                comment.setAccountId(resultSet.getLong("account_id"));
                comment.setPostId(resultSet.getLong("post_id"));
                comment.setText(resultSet.getString("text"));
                comment.setCreatedAt(resultSet.getTimestamp("created_at"));
                comments.add(comment);
            }

            return comments;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

package by.tms.socialprojectc38onl.dao;

import by.tms.socialprojectc38onl.PgConnection;
import by.tms.socialprojectc38onl.models.Account;
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
                    ("INSERT INTO post_comments (account_id,post_id,text) VALUES(?,?,?)");
            preparedStatement.setLong(1, comment.getAccountId());
            preparedStatement.setLong(2, comment.getPostId());
            preparedStatement.setString(3, comment.getText());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Comment> findById(int id) {
        try (Connection connection = PgConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("SELECT * FROM post_comments WHERE id = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Comment comment = new Comment();
                comment.setId(resultSet.getInt("id"));
                comment.setAccountId(resultSet.getInt("account_id"));
                comment.setPostId(resultSet.getInt("post_id"));
                comment.setText(resultSet.getString("text"));
                comment.setCreatedAt(resultSet.getTimestamp("created_at"));

                return Optional.of(comment);
            }
            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Comment> findByPostId(int postId) {
        List<Comment> comments = new ArrayList<>();
        try (Connection connection = PgConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("SELECT pc.id AS comment_id, pc.account_id, pc.post_id, pc.text, pc.created_at AS comment_created_at, " +
                            "acc.id AS account_id, acc.nickname, acc.email " +
                            "FROM post_comments pc " +
                            "JOIN accounts acc ON acc.id = pc.account_id " +
                            "WHERE pc.post_id = ?");
            preparedStatement.setLong(1, postId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Account account = new Account();
                Comment comment = new Comment();

                account.setId(resultSet.getInt("account_id"));
                account.setNickname(resultSet.getString("nickname"));
                account.setEmail(resultSet.getString("email"));

                comment.setId(resultSet.getInt("comment_id"));
                comment.setAccountId(resultSet.getInt("account_id"));
                comment.setPostId(resultSet.getInt("post_id"));
                comment.setText(resultSet.getString("text"));
                comment.setCreatedAt(resultSet.getTimestamp("comment_created_at"));
                comment.setAccount(account);
                comments.add(comment);
            }

            return comments;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

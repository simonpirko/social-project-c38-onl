package by.tms.socialprojectc38onl.dao;

import by.tms.socialprojectc38onl.PgConnection;
import by.tms.socialprojectc38onl.models.Account;
import by.tms.socialprojectc38onl.models.Comment;
import by.tms.socialprojectc38onl.models.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DAOPosts {

    private static DAOPosts INSTANCE;

    private DAOPosts() {
    }

    public static DAOPosts getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DAOPosts();
        }
        return INSTANCE;
    }

    public void save(Post post) throws SQLException {
        try (Connection connection = PgConnection.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO posts(title, description, account_id, images) VALUES (?,?,?,?)");
            preparedStatement.setString(1, post.getTitle());
            preparedStatement.setString(2, post.getDescription());
            preparedStatement.setInt(3, post.getAccountID());
            preparedStatement.setString(4, post.getImages());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Post> findById(int id) {
        try (Connection connection = PgConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT a.id AS post_id, a.created_at AS post_created_at, a.title, a.description, a.images, c.id AS comment_id, c.created_at AS comment_created_at, c.text FROM posts a JOIN post_comments c ON a.id = c.post_id WHERE a.id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Post post = new Post();
                List<Comment> comments = new ArrayList<>();

                post.setId(resultSet.getInt("post_id"));
                post.setTitle(resultSet.getString("title"));
                post.setDescription(resultSet.getString("description"));
                post.setCreateAt(resultSet.getTimestamp("post_created_at"));
                post.setImages(resultSet.getString("images"));

                while (resultSet.next()) {
                    Comment comment = new Comment();
                    comment.setId(resultSet.getLong("comment_id"));
                    comment.setText(resultSet.getString("text"));
                    comment.setCreatedAt(resultSet.getTimestamp("comment_created_at"));
                    comments.add(comment);
                }

                post.setComments(comments);

                return Optional.of(post);
            }

            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

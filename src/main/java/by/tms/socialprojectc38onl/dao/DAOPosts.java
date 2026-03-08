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

    public String save(Post post) {
        try (Connection connection = PgConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO posts(title, description, account_id, images) VALUES (?,?,?,?) RETURNING id");
            preparedStatement.setString(1, post.getTitle());
            preparedStatement.setString(2, post.getDescription());
            preparedStatement.setInt(3, post.getAccountID());
            preparedStatement.setString(4, post.getImages());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    return resultSet.getString("id");
                } else {
                    throw new SQLException("Insert failed: no id returned");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Post> findById(Integer id) {
        try (Connection connection = PgConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT p.id AS post_id, p.created_at AS post_created_at, p.title, p.description, p.images, c.id AS comment_id, c.created_at AS comment_created_at, c.text FROM posts p LEFT JOIN post_comments c ON p.id = c.post_id WHERE p.id = ?");
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
                    comment.setId(resultSet.getInt("comment_id"));
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

    public List<Post> findAll() {
        try (Connection connection = PgConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM posts join accounts on accounts.id = posts.account_id");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Post> posts = new ArrayList<>();
            while (resultSet.next()) {
                Post post = new Post();
                Account account = new Account();

                account.setId(resultSet.getInt("account_id"));
                account.setPassword(resultSet.getString("password"));
                account.setCreateAt(resultSet.getTimestamp("created_at"));
                account.setNickname(resultSet.getString("nickname"));
                account.setEmail(resultSet.getString("email"));

                post.setId(resultSet.getInt("id"));
                post.setTitle(resultSet.getString("title"));
                post.setDescription(resultSet.getString("description"));
                post.setCreateAt(resultSet.getTimestamp("created_at"));
                post.setAccountID(resultSet.getInt("account_id"));
                post.setImages(resultSet.getString("images"));
                post.setAccount(account);

                posts.add(post);
            }
            return posts;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Post> findByAccountId(int accountId) {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT * FROM posts WHERE account_id = ?";

        try (Connection connection = PgConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, accountId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Post post = new Post();
                post.setId(resultSet.getInt("id"));
                post.setTitle(resultSet.getString("title"));
                post.setDescription(resultSet.getString("description"));
                post.setImages(resultSet.getString("images"));
                post.setAccountID(resultSet.getInt("account_id"));
                posts.add(post);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return posts;
    }

    public List<Post> findByTitle(String title) {
        try (Connection connection = PgConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM posts JOIN accounts ON accounts.id = posts.account_id WHERE title ILIKE '%' || ? || '%'");
            preparedStatement.setString(1, title);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Post> posts = new ArrayList<>();
            while (resultSet.next()) {
                Post post = new Post();
                Account account = new Account();
                account.setId(resultSet.getInt("account_id"));
                account.setPassword(resultSet.getString("password"));
                account.setCreateAt(resultSet.getTimestamp("created_at"));
                account.setNickname(resultSet.getString("nickname"));
                account.setEmail(resultSet.getString("email"));

                post.setId(resultSet.getInt("id"));
                post.setTitle(resultSet.getString("title"));
                post.setDescription(resultSet.getString("description"));
                post.setCreateAt(resultSet.getTimestamp("created_at"));
                post.setAccountID(resultSet.getInt("account_id"));
                post.setImages(resultSet.getString("images"));
                post.setAccount(account);

                posts.add(post);
            }

            return posts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int countByAccountId(int accountId) {
        String sql = "SELECT COUNT(*) FROM posts WHERE account_id = ?";
        try (Connection connection = PgConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, accountId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

}

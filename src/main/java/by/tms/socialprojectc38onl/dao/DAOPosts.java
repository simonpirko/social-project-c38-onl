package by.tms.socialprojectc38onl.dao;

import by.tms.socialprojectc38onl.PgConnection;
import by.tms.socialprojectc38onl.models.Account;
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

    public Optional<Post> findById(int id) {
        try (Connection connection = PgConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM posts WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Post post = new Post();

                post.setId(resultSet.getInt("id"));
                post.setTitle(resultSet.getString("title"));
                post.setDescription(resultSet.getString("description"));
                post.setCreateAt(resultSet.getTimestamp("created_at"));
                post.setAccountID(resultSet.getInt("account_id"));
                post.setImages(resultSet.getString("images"));

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
}

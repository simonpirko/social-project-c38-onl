package by.tms.socialprojectc38onl.dao;

import by.tms.socialprojectc38onl.PgConnection;
import by.tms.socialprojectc38onl.models.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class SubscribeDAO {
    private static SubscribeDAO INSTANCE;

    private SubscribeDAO() {}

    public static synchronized SubscribeDAO getInstance() {
        if (Objects.isNull(INSTANCE)) {
            INSTANCE = new SubscribeDAO();
        }
        return INSTANCE;
    }

    public boolean isFollowing(Integer followerId, Integer followingId) {
        String sql = "SELECT FROM subscriptions WHERE follower_id = ? AND following_id = ?";
        try(Connection connection = PgConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, followerId);
            preparedStatement.setInt(2, followingId);
            ResultSet resultSet = preparedStatement.executeQuery();

            return (resultSet.next());
        } catch (SQLException e) {
            throw new RuntimeException("Error finding subscription", e);
        }
    }

    public void save(Integer followerId, Integer followingId) {
        String sql = "INSERT INTO subscriptions(follower_id, following_id) VALUES (?,?)";
        try(Connection connection = PgConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                    preparedStatement.setInt(1, followerId);
                    preparedStatement.setInt(2, followingId);
                    preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error saving subscription", e);
        }
    }

    public void delete(Integer followerId, Integer followingId) {
        String sql = "DELETE FROM subscriptions WHERE follower_id = ? AND following_id = ?";
        try(Connection connection = PgConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, followerId);
            preparedStatement.setInt(2, followingId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting subscription", e);
        }
    }

    public int countFollowers(Integer userId) {
        String sql = "SELECT COUNT(*) FROM subscriptions WHERE following_id = ?";
        return getCount(userId, sql);
    }

    public int countFollowing(Integer userId) {
        String sql = "SELECT COUNT(*) FROM subscriptions WHERE follower_id = ?";
        return getCount(userId, sql);
    }

    public List<Account> getFollowers(Integer userId) {
        String sql = """
                SELECT a.*
                FROM accounts a
                INNER JOIN subscriptions s ON a.id = s.follower_id
                WHERE s.following_id = ?
            """;

        try(Connection connection = PgConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Account> followers = new ArrayList<>();

            while(resultSet.next()) {
                Account follower = new Account();
                follower.setId(resultSet.getInt("id"));
                follower.setPassword(resultSet.getString("password"));
                follower.setCreateAt(resultSet.getTimestamp("created_at"));
                follower.setNickname(resultSet.getString("nickname"));
                follower.setEmail(resultSet.getString("email"));

                followers.add(follower);
            }
            return followers;
        } catch (SQLException e) {
            throw new RuntimeException("Error extracting followers", e);
        }
    }

    public List<Account> getFollowings(Integer userId) {
        String sql = """
                SELECT a.*
                FROM accounts a
                INNER JOIN subscriptions s ON a.id = s.following_id
                WHERE s.follower_id = ?
            """;

        try(Connection connection = PgConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Account> followings = new ArrayList<>();

            while(resultSet.next()) {
                Account following = new Account();
                following.setId(resultSet.getInt("id"));
                following.setPassword(resultSet.getString("password"));
                following.setCreateAt(resultSet.getTimestamp("created_at"));
                following.setNickname(resultSet.getString("nickname"));
                following.setEmail(resultSet.getString("email"));

                followings.add(following);
            }
            return followings;
        } catch (SQLException e) {
            throw new RuntimeException("Error extracting followers", e);
        }
    }

    private int getCount(Integer userId, String sql) {
        try (Connection connection = PgConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error counting subscriptions", e);
        }
        return 0;
    }


}

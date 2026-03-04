package by.tms.socialprojectc38onl.dao;

import by.tms.socialprojectc38onl.PgConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

    public void save(int followerId, int followingId) {
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

    public void delete(int followerId, int followingId) {
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
}

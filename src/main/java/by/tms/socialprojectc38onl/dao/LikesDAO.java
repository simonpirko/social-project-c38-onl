package by.tms.socialprojectc38onl.dao;

import by.tms.socialprojectc38onl.PgConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class LikesDAO {
    private static LikesDAO INSTANCE;

    private LikesDAO() {
    }

    public static LikesDAO getInstance() {
        if (Objects.isNull(INSTANCE)) {
            INSTANCE = new LikesDAO();
        }
        return INSTANCE;
    }

    public void addLike(int post_id, int account_id) throws SQLException {
        try (Connection connection = PgConnection.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO post_likes(post_id, account_id) VALUES (?,?)");
            preparedStatement.setInt(1, post_id);
            preparedStatement.setInt(2, account_id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeLike(int postId, int accountId) {
        try (Connection connection = PgConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM post_likes WHERE post_id = ? AND account_id = ?")) {

            preparedStatement.setInt(1, postId);
            preparedStatement.setInt(2, accountId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

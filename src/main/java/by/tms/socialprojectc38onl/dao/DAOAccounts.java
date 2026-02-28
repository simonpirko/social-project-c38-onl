package by.tms.socialprojectc38onl.dao;

import by.tms.socialprojectc38onl.PgConnection;
import by.tms.socialprojectc38onl.models.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class DAOAccounts {
    private static DAOAccounts INSTANCE;

    private DAOAccounts() {
    }

    public static DAOAccounts getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DAOAccounts();
        }

        return INSTANCE;
    }

    public void save(Account account) {
        try (Connection connection = PgConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("INSERT INTO accounts (nickname,email,password,created_at) VALUES(?,?,?,?)");
            preparedStatement.setString(1, account.getNickname());
            preparedStatement.setString(2, account.getEmail());
            preparedStatement.setString(3, account.getPassword());
            preparedStatement.setTimestamp(4, new java.sql.Timestamp(System.currentTimeMillis()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
  
    public Optional<Account> findByEmail(String email) {
        try (Connection connection = PgConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("SELECT * FROM accounts WHERE email = ?");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Account account = new Account();

                account.setEmail(resultSet.getString("email"));
                account.setNickname(resultSet.getString("nickname"));
                account.setPassword(resultSet.getString("password"));

                return Optional.of(account);
            }

            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

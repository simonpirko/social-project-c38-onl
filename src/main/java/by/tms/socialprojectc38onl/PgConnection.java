package by.tms.socialprojectc38onl;

import by.tms.socialprojectc38onl.util.ApplicationProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PgConnection {
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    ApplicationProperties.get("datasource.url"),
                    ApplicationProperties.get("datasource.username"),
                    ApplicationProperties.get("datasource.password")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
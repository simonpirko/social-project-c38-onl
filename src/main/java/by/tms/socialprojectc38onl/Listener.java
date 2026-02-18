package by.tms.socialprojectc38onl;

import by.tms.socialprojectc38onl.util.ApplicationProperties;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.flywaydb.core.Flyway;

@WebListener
public class Listener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Flyway load = Flyway
                .configure()
                .dataSource(
                        ApplicationProperties.get("datasource.url"),
                        ApplicationProperties.get("datasource.username"),
                        ApplicationProperties.get("datasource.password")
                )
                .load();

        load.migrate();
    }
}

package config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.NonNull;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;

public class HikariConnectionPool {
    private static HikariDataSource dataSource;

    static {
        try {
            Path path = Paths.get(HikariConnectionPool.class.getClassLoader().getResource("database/database.sqlite").toURI());
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl("jdbc:sqlite:" + path);
            config.setDriverClassName("org.sqlite.JDBC");
            config.setMaximumPoolSize(5);
            config.setConnectionTimeout(10000);

            dataSource = new HikariDataSource(config);
        } catch (URISyntaxException | NullPointerException e) {
            throw new RuntimeException("Не удалось загрузить базу данных.");
        }
    }

    public static Connection getConnection() {
        Connection connection;
        try {
            connection = dataSource.getConnection();
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка в соединении с БД");
        }
    }
}

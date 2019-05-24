package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.Server;

public class DatabaseUtil {
    private static Server db;
    private static JdbcDataSource ds = new JdbcDataSource();

    private DatabaseUtil() {
    }

    public static synchronized void initDatabase() throws SQLException {
        if (Objects.isNull(db)) {
            db = Server.createTcpServer().start();

            ds.setURL("jdbc:h2:mem:mockdb;DB_CLOSE_DELAY=-1");
            // ds.setUser("user");
            // ds.setPassword("password");

            try (Connection conn = DatabaseUtil.getConnection();
                Statement stmt = conn.createStatement()) {

                Files.lines(Paths.get("mock_data.sql"))
                    .filter(line -> !line.isEmpty())
                    .forEach(line -> {
                        try {
                            stmt.executeUpdate(line);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    });

                stmt.close();
                conn.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

    public static synchronized Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
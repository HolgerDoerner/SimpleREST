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

/**
 * Implementation encapsulates an H2 in-memmory database.
 */
public class DatabaseProvider {
    private static Server db;
    private static JdbcDataSource ds = new JdbcDataSource();

    private static final Object lock = new Object();

    private DatabaseProvider() {
    }

    public static void initDatabase() throws SQLException {
        if (Objects.isNull(db)) {
            synchronized (lock) {
                if (Objects.isNull(db)) {
                    db = Server.createTcpServer().start();

                    ds.setURL("jdbc:h2:mem:mockdb;DB_CLOSE_DELAY=-1");

                    try (Connection conn = ds.getConnection(); Statement stmt = conn.createStatement()) {
                        Files.lines(Paths.get("mock_data.sql")).filter(line -> !line.isEmpty()).forEach(line -> {
                            try {
                                stmt.executeUpdate(line);
                            } catch (SQLException e) {
                                System.err.println("Error setting up database: " + e.getMessage());
                                System.err.println("Exiting...");
                                System.exit(1);
                            }
                        });
                    } catch (IOException e) {
                        System.err.println("Unable to read SQL file: " + e.getMessage());
                        System.err.println("Make sure it is placedin the same directory as the JAR file!");
                        System.err.println("Exiting...");
                        System.exit(1);
                    }
                }
            }
        }
    }

    public static Connection getConnection() throws SQLException {
        if (Objects.isNull(db)) {
            initDatabase();
        }

        return ds.getConnection();
    }
}
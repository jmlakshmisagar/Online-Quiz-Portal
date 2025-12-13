package com.quiz.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBConnection {
    // Use root directly
    private static final String URL = "jdbc:mysql://localhost:3306/quiz_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASS = "root@39";
    private static volatile boolean driverLoaded = false;

    private DBConnection() {}

    private static void loadDriver() {
        if (!driverLoaded) {
            synchronized (DBConnection.class) {
                if (!driverLoaded) {
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        driverLoaded = true;
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    public static Connection getConnection() throws SQLException {
        loadDriver();
        return DriverManager.getConnection(URL, USER, PASS);
    }
}

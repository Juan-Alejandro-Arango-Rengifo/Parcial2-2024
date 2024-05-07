package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDatos {
    private static String url = "jdbc:mysql://localhost:3306/parcial2?serverTimezone=America/Bogota";
    private static  String username = "root";
    private static String password = "arango";
    private static Connection connection;

    public static Connection getInstace() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(url, username, password);
        }
        return connection;
    }
}

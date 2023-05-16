package com.mycompany.swing.dominio.entidades;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Table {
    public static boolean isTableEmpty(Connection connection, String tableName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String query = "SELECT COUNT(*) FROM " + tableName;
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            int count = resultSet.getInt(1);
            return count == 0;
        }
    }
}

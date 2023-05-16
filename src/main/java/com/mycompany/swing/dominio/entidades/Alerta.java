package com.mycompany.swing.dominio.entidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Alerta {
    public static void insertAlerta(Connection connection, double minCpu, double maxCpu, double minDisco, double maxDisco, double minMemoria, double maxMemoria, double minRede, double maxRede, int fkEmpresa) throws SQLException {
        String query = "INSERT INTO alerta (minCpu, maxCpu, minDisco, maxDisco, minMemoria, maxMemoria, minRede, maxRede, fkEmpresa) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDouble(1, minCpu);
            statement.setDouble(2, maxCpu);
            statement.setDouble(3, minDisco);
            statement.setDouble(4, maxDisco);
            statement.setDouble(5, minMemoria);
            statement.setDouble(6, maxMemoria);
            statement.setDouble(7, minRede);
            statement.setDouble(8, maxRede);
            statement.setInt(9, fkEmpresa);
            statement.executeUpdate();
        }
    }
}
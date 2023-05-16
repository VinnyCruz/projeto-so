package com.mycompany.swing.dominio.entidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Empresa {
    public static void insertEmpresa(Connection connection, String nomeFantasia, String cnpj, String emailResponsavel, String telefone) throws SQLException {
        String query = "INSERT INTO empresa (nomeFantasia, cnpj, emailResponsavel, telefone) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, nomeFantasia);
            statement.setString(2, cnpj);
            statement.setString(3, emailResponsavel);
            statement.setString(4, telefone);
            statement.executeUpdate();
        }    }
}
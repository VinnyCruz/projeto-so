package com.mycompany.swing.dominio.entidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Notebook {
    public static void insertNotebook(Connection connection, String marca, String modelo, String capacidadeRam, String tipoDisco, String velocidadeCpu, int fkUsuario, int fkEmpresa) throws SQLException {
        String query = "INSERT INTO notebook (marca, modelo, capacidadeRam, tipoDisco, velocidadeCpu, fkUsuario, fkEmpresa) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, marca);
            statement.setString(2, modelo);
            statement.setString(3, capacidadeRam);
            statement.setString(4, tipoDisco);
            statement.setString(5, velocidadeCpu);
            statement.setInt(6, fkUsuario);
            statement.setInt(7, fkEmpresa);
            statement.executeUpdate();
        }
    }
}

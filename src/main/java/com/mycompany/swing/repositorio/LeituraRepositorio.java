package com.mycompany.swing.repositorio;

import com.mycompany.swing.dominio.Leitura;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

public class LeituraRepositorio {

    final private JdbcTemplate jdbcTemplate;
    final private Connection jdbcTemplateMySQL;

    public LeituraRepositorio(JdbcTemplate jdbcTemplate, Connection jdbcTemplateMySQL) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcTemplateMySQL = jdbcTemplateMySQL;
    }

    public void inserir(Leitura leitura) throws Exception {
        jdbcTemplate.update("""
                            INSERT INTO dadosCapturados (qtdUsadaRam, tempoAtvDisco, tempoAtvCpu, utilizacaoCpu, qtdProcessoCpu, qtdThreadsCpu, dataHora, fkNotebook, fkUsuario, fkEmpresa) VALUES
                            (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                        """, leitura.getQtdUsadaRam(), leitura.getTempoAtvDisco(), leitura.getTempoAtvCpu(), leitura.getUtilizacaoCpu(),
                leitura.getQtdProcessoCpu(), leitura.getQtdThreadsCpu(), leitura.getDataHora(), leitura.getFkNotebook(),
                leitura.getFkUsuario(), leitura.getFkEmpresa());

        PreparedStatement stmt = jdbcTemplateMySQL.prepareStatement("INSERT INTO dadosCapturados (qtdUsadaRam, tempoAtvDisco, tempoAtvCpu, utilizacaoCpu, qtdProcessoCpu, qtdThreadsCpu, dataHora, fkNotebook, fkUsuario, fkEmpresa) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        stmt.setString(1, leitura.getQtdUsadaRam());
        stmt.setString(2, leitura.getTempoAtvDisco());
        stmt.setString(3, leitura.getTempoAtvCpu());
        stmt.setString(4, leitura.getUtilizacaoCpu());
        stmt.setString(5, leitura.getQtdProcessoCpu());
        stmt.setString(6, leitura.getQtdThreadsCpu());
        stmt.setTimestamp(7, Timestamp.valueOf(leitura.getDataHora()));
        stmt.setLong(8, leitura.getFkNotebook());
        stmt.setLong(9, leitura.getFkUsuario());
        stmt.setLong(10, leitura.getFkEmpresa());
        stmt.executeUpdate();
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.swing;

import com.mycompany.swing.config.ConexaoMySQL;
import com.mycompany.swing.config.ConexaoMySQLInit;
import com.mycompany.swing.config.ConexaoSQLServer;
import com.mycompany.swing.dominio.Leitura;
import com.mycompany.swing.dominio.Usuario;
import com.mycompany.swing.dominio.entidades.Table;
import com.mycompany.swing.repositorio.LeituraRepositorio;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.mycompany.swing.dominio.entidades.Alerta.insertAlerta;
import static com.mycompany.swing.dominio.entidades.Empresa.insertEmpresa;
import static com.mycompany.swing.dominio.entidades.Notebook.insertNotebook;
import static com.mycompany.swing.dominio.entidades.Usuario.insertUsuario;


public class app {

    public static void main(String args[]) {
        String nomeDigitado = "Nathalia";
        String senhaDigitada = "Acessar@";
        System.out.println("Usuário:" + nomeDigitado);

        ConexaoSQLServer conexaoSQLServer = new ConexaoSQLServer();
        JdbcTemplate con = conexaoSQLServer.getConexaoDoBanco();

        System.out.println("Conexão com bando de dados SQL Server efetuada com sucesso!");

        ConexaoMySQLInit conexaoMySQLInit = new ConexaoMySQLInit();
        Connection connInit = conexaoMySQLInit.getJdbcTemplateMySQL();

        try {
            Statement statement = connInit.createStatement();

            String[] sqlCommands = {
                    "CREATE DATABASE IF NOT EXISTS monitoramento",
                    "USE monitoramento",
                    "CREATE TABLE IF NOT EXISTS empresa(" +
                            "idEmpresa INT PRIMARY KEY AUTO_INCREMENT," +
                            "nomeFantasia VARCHAR(45)," +
                            "cnpj CHAR(15)," +
                            "emailResponsavel VARCHAR(35)," +
                            "senha VARCHAR(45)," +
                            "telefone VARCHAR(45)" +
                            ")",
                    "CREATE TABLE IF NOT EXISTS alerta(" +
                            "idAlerta INT PRIMARY KEY AUTO_INCREMENT," +
                            "minCpu DECIMAL(10,2)," +
                            "maxCpu DECIMAL(10,2)," +
                            "minDisco DECIMAL(10,2)," +
                            "maxDisco DECIMAL(10,2)," +
                            "minMemoria DECIMAL(10,2)," +
                            "maxMemoria DECIMAL(10,2)," +
                            "minRede DECIMAL(10,2)," +
                            "maxRede DECIMAL(10,2)," +
                            "fkEmpresa INT," +
                            "FOREIGN KEY (fkEmpresa) REFERENCES empresa(idEmpresa)" +
                            ")",
                    "CREATE TABLE IF NOT EXISTS usuario(" +
                            "idUsuario INT PRIMARY KEY AUTO_INCREMENT," +
                            "nome VARCHAR(45)," +
                            "login VARCHAR(35)," +
                            "senha VARCHAR(45)," +
                            "tipo VARCHAR(45)," +
                            "fkEmpresa INT," +
                            "FOREIGN KEY (fkEmpresa) REFERENCES empresa(idEmpresa)" +
                            ")",
                    "CREATE TABLE IF NOT EXISTS notebook(" +
                            "idNotebook INT PRIMARY KEY AUTO_INCREMENT," +
                            "marca VARCHAR(45)," +
                            "modelo VARCHAR(45)," +
                            "capacidadeRam VARCHAR(45)," +
                            "tipoDisco VARCHAR(45)," +
                            "velocidadeCpu VARCHAR(45)," +
                            "fkUsuario INT," +
                            "fkEmpresa INT," +
                            "FOREIGN KEY (fkUsuario) REFERENCES usuario(idUsuario)," +
                            "FOREIGN KEY (fkEmpresa) REFERENCES empresa(idEmpresa)" +
                            ")",
                    "CREATE TABLE IF NOT EXISTS dadosCapturados(" +
                            "idDadosCapturados INT PRIMARY KEY AUTO_INCREMENT," +
                            "qtdUsadaRam VARCHAR(45)," +
                            "tempoAtvDisco VARCHAR(45)," +
                            "tempoAtvCpu VARCHAR(45)," +
                            "utilizacaoCpu VARCHAR(45)," +
                            "qtdProcessoCpu VARCHAR(45)," +
                            "qtdThreadsCpu VARCHAR(45)," +
                            "dataHora DATETIME," +
                            "fkNotebook INT," +
                            "fkUsuario INT," +
                            "fkEmpresa INT," +
                            "FOREIGN KEY (fkNotebook) REFERENCES notebook(idNotebook)," +
                            "FOREIGN KEY (fkUsuario) REFERENCES usuario(idUsuario)," +
                            "FOREIGN KEY (fkEmpresa) REFERENCES empresa(idEmpresa)" +
                            ")"
            };

            for (String sqlCommand : sqlCommands) {
                statement.executeUpdate(sqlCommand);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Logger.getLogger(app.class.getName()).log(Level.SEVERE, "Erro na pausa de thread.", e);
                }
            }

            if (Table.isTableEmpty(connInit, "empresa")) {
                insertEmpresa(connInit, "Acflazz", "123456789012365", "acflazz@gmail.com", "11974748563");
                insertEmpresa(connInit, "Juliana", "123456789123456", "juliana@gmail.com", "11355447789");
                insertEmpresa(connInit, "ICE", "123456789123456", "ice@gmail.com", "11234567897");
                insertEmpresa(connInit, "Gesso", "08338976000180", "erick.tricolor9@gmail.com", "11951534515");
                insertEmpresa(connInit, "Apple", "30381362000130", "erick.tricolor9@gmail.com", "11951534515");
            }

            if (Table.isTableEmpty(connInit, "usuario")) {
                insertUsuario(connInit, "Erick", "erick@gmail.com", "Acessar@", "root", 1);
                insertUsuario(connInit, "Juliana", "juliana@gmail.com", "Acessar@", "root", 2);
                insertUsuario(connInit, "Pytel", "pytel@gmail.com", "Acessar@", "suporte", 2);
                insertUsuario(connInit, "Matheus", "matheus@gmail.com", "Acessar@", "atendente", 2);
                insertUsuario(connInit, "Leonardo", "leo@gmail.com", "Acessar@", "root", 3);
                insertUsuario(connInit, "Nathalia", "nathalia.obi@gmail.com", "Acessar@", "root", 5);
            }

            if (Table.isTableEmpty(connInit, "notebook")) {
                insertNotebook(connInit, "Dell", "Inspiron 15", "8GB", "SSD", "3.1 GHz", 1, 1);
                insertNotebook(connInit, "Lenovo", "Thinkpad X1 Carbon", "16GB", "NVMe SSD", "1.6 GHz", 2, 2);
                insertNotebook(connInit, "Asus", "ZenBook UX425EA", "16GB", "PCIe SSD", "2.4 GHz", 3, 2);
                insertNotebook(connInit, "Acer", "Aspire 5", "8GB", "HDD", "1.1 GHz", 4, 2);
                insertNotebook(connInit, "HP", "EliteBook 840 G3", "8GB", "SSD", "1.9 GHz", 5, 3);
                insertNotebook(connInit, "Dell", "XPS 13", "16GB", "NVMe SSD", "3.0 GHz", 6, 5);
            }

            if (Table.isTableEmpty(connInit, "alerta")) {
                insertAlerta(connInit, 20.0, 80.0, 10.0, 90.0, 20.0, 80.0, 10.0, 90.0, 1);
                insertAlerta(connInit, 15.0, 75.0, 5.0, 85.0, 15.0, 75.0, 5.0, 85.0, 2);
                insertAlerta(connInit, 25.0, 85.0, 15.0, 95.0, 25.0, 85.0, 15.0, 95.0, 3);
                insertAlerta(connInit, 18.0, 78.0, 8.0, 88.0, 18.0, 78.0, 8.0, 88.0, 4);
                insertAlerta(connInit, 22.0, 82.0, 12.0, 92.0, 22.0, 82.0, 12.0, 92.0, 5);
            }

            ConexaoMySQL conexaoMySQL = new ConexaoMySQL();
            Connection conn = conexaoMySQL.getJdbcTemplateMySQL();

            LeituraRepositorio leituraRepositorio = new LeituraRepositorio(con, conn);

            try {
                Usuario listaUsuario = con.queryForObject("SELECT * FROM usuario WHERE nome = ? AND senha = ?",
                        new BeanPropertyRowMapper<>(Usuario.class), nomeDigitado, senhaDigitada);

                System.out.println("Usuário logado! Capturando dados...");

                String selectNotebook = "SELECT idNotebook, fkEmpresa FROM notebook WHERE fkUsuario = ?";
                assert listaUsuario != null;
                Map<String, Object> notebookMap = con.queryForMap(selectNotebook, listaUsuario.getIdUsuario());
                int idNotebook = (int) notebookMap.get("idNotebook");
                int fkEmpresa = (int) notebookMap.get("fkEmpresa");

                new Timer().scheduleAtFixedRate(new TimerTask() {
                    final SystemInfo si = new SystemInfo();
                    final HardwareAbstractionLayer hal = si.getHardware();
                    final OperatingSystem os = si.getOperatingSystem();
                    final CentralProcessor processor = hal.getProcessor();

                    @Override
                    public void run() {
                        try {
                            System.out.println("Dados Capturados:");
                            GlobalMemory memory = hal.getMemory();
                            long memoriaEmUso = memory.getAvailable();
                            long memoriaCap = memory.getTotal();
                            long percentualMemoria = (memoriaEmUso * 100) / memoriaCap;
                            String percentualMemoriaStr = String.valueOf(percentualMemoria);
                            System.out.println("Percentual de Memoria: " + percentualMemoriaStr + "%");

                            HWDiskStore[] diskStores = hal.getDiskStores().toArray(new HWDiskStore[0]);
                            long tempoAtivoDisco = diskStores[0].getTimeStamp() / 1000;
                            String tempoAtivoDiscoStr = String.valueOf(tempoAtivoDisco);
                            System.out.println("Tempo ativo do disco: " + tempoAtivoDiscoStr + " segundos");

                            long uptime = os.getSystemUptime();
                            String uptimeStr = String.valueOf(uptime);
                            System.out.println("Tempo de ativo da CPU: " + uptimeStr + " segundos");

                            double cpuUsage = processor.getSystemCpuLoad(1000) * 100;
                            String utilizacaoCPUStr = String.format("%.2f", cpuUsage);
                            System.out.println("Porcentagem de Uso CPU: " + utilizacaoCPUStr + "%");

                            int processCount = os.getProcessCount();
                            String processCountStr = String.valueOf(processCount);
                            System.out.println("Quantidade de processos: " + processCountStr);

                            int threadCount = os.getThreadCount();
                            String threadCountStr = String.valueOf(threadCount);
                            System.out.println("Quantidade de threads: " + threadCountStr);

                            LocalDateTime dataHoraConsulta = LocalDateTime.now();
                            System.out.println("Data e Hora da Consulta:" + dataHoraConsulta);
                            System.out.println("--------------");

                            Leitura leitura = new Leitura(
                                    percentualMemoriaStr,
                                    tempoAtivoDiscoStr,
                                    uptimeStr,
                                    utilizacaoCPUStr,
                                    processCountStr,
                                    threadCountStr,
                                    dataHoraConsulta,
                                    idNotebook,
                                    listaUsuario.getIdUsuario(),
                                    fkEmpresa
                            );

                            leituraRepositorio.inserir(leitura);

                        } catch (Exception e) {
                            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Erro ao capturar dados", e);
                        }
                    }
                }, 0, 10000);
            } catch (EmptyResultDataAccessException e) {
                Logger.getLogger(app.class.getName()).log(Level.SEVERE, "Usuario e/ou senha incorretos.", e);
            }
        } catch (SQLException e) {
            Logger.getLogger(app.class.getName()).log(Level.SEVERE, "Erro durante a execução!", e);
            throw new RuntimeException(e);
        }
    }
}

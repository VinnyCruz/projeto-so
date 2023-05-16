package com.mycompany.swing.dominio;

import java.time.LocalDateTime;

public class Leitura {
    final private String qtdUsadaRam;
    final private String tempoAtvDisco;
    final private String tempoAtvCpu;
    final private String utilizacaoCpu;
    final private String qtdProcessoCpu;
    final private String qtdThreadsCpu;
    final private LocalDateTime dataHora;
    private int fkNotebook;
    private int fkUsuario;
    private int fkEmpresa;

    public Leitura(String qtdUsadaRam, String tempoAtvDisco, String tempoAtvCpu, String utilizacaoCpu,
                   String qtdProcessoCpu, String qtdThreadsCpu, LocalDateTime dataHora, int fkNotebook, int fkUsuario,
                   int fkEmpresa) {
        this.qtdUsadaRam = qtdUsadaRam;
        this.tempoAtvDisco = tempoAtvDisco;
        this.tempoAtvCpu = tempoAtvCpu;
        this.utilizacaoCpu = utilizacaoCpu;
        this.qtdProcessoCpu = qtdProcessoCpu;
        this.qtdThreadsCpu = qtdThreadsCpu;
        this.dataHora = dataHora;
        this.fkNotebook = fkNotebook;
        this.fkUsuario = fkUsuario;
        this.fkEmpresa = fkEmpresa;
    }

    public void setFkNotebook(int fkNotebook) {
        this.fkNotebook = fkNotebook;
    }

    public void setFkUsuario(int fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    public void setFkEmpresa(int fkEmpresa) {
        this.fkEmpresa = fkEmpresa;
    }

    public String getQtdUsadaRam() {
        return qtdUsadaRam;
    }

    public String getTempoAtvDisco() {
        return tempoAtvDisco;
    }

    public String getTempoAtvCpu() {
        return tempoAtvCpu;
    }

    public String getUtilizacaoCpu() {
        return utilizacaoCpu;
    }

    public String getQtdProcessoCpu() {
        return qtdProcessoCpu;
    }

    public String getQtdThreadsCpu() {
        return qtdThreadsCpu;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public int getFkNotebook() {
        return fkNotebook;
    }

    public int getFkUsuario() {
        return fkUsuario;
    }

    public int getFkEmpresa() {
        return fkEmpresa;
    }
}

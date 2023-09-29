package Entities;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table (name = "WorldCups")
public class CopaDoMundo {
    @Id
    @Column (name = "Year")
    private Integer ano;
    @Column (name = "Country")
    private String paisSede;
    @Column (name = "Winner")
    private String campeao;
    @Column (name = "RunnersUp")
    private String viceCampeao;
    @Column (name = "Third")
    private String terceiroColocado;
    @Column (name = "Fourth")
    private String quartoColocado;
    @Column (name = "GoalsScored")
    private int golsMarcados;
    @Column (name = "QualifiedTeams")
    private int qtTimes;
    @Column (name = "MatchesPlayed")
    private int qtPartidas;
    @Column (name = "Attendance")
    private String publico;

    public CopaDoMundo() {
    }

    public CopaDoMundo(Integer ano, String paisSede, String campeao, String viceCampeao) {
        this.ano = ano;
        this.paisSede = paisSede;
        this.campeao = campeao;
        this.viceCampeao = viceCampeao;
    }

    public CopaDoMundo(Integer ano, String paisSede, String campeao, String viceCampeao, String terceiroColocado, String quartoColocado) {
        this.ano = ano;
        this.paisSede = paisSede;
        this.campeao = campeao;
        this.viceCampeao = viceCampeao;
        this.terceiroColocado = terceiroColocado;
        this.quartoColocado = quartoColocado;
    }

    public CopaDoMundo(int ano) {
        this.ano = ano;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getPaisSede() {
        return paisSede;
    }

    public void setPaisSede(String paisSede) {
        this.paisSede = paisSede;
    }

    public String getCampeao() {
        return campeao;
    }

    public void setCampeao(String campeao) {
        this.campeao = campeao;
    }

    public String getViceCampeao() {
        return viceCampeao;
    }

    public String getTerceiroColocado() {
        return terceiroColocado;
    }

    public void setTerceiroColocado(String terceiroColocado) {
        this.terceiroColocado = terceiroColocado;
    }

    public String getQuartoColocado() {
        return quartoColocado;
    }

    public void setQuartoColocado(String quartoColocado) {
        this.quartoColocado = quartoColocado;
    }

    public int getGolsMarcados() {
        return golsMarcados;
    }

    public void setGolsMarcados(int golsMarcados) {
        this.golsMarcados = golsMarcados;
    }

    public int getQtTimes() {
        return qtTimes;
    }

    public void setQtTimes(int qtTimes) {
        this.qtTimes = qtTimes;
    }

    public int getQtPartidas() {
        return qtPartidas;
    }

    public void setQtPartidas(int qtPartidas) {
        this.qtPartidas = qtPartidas;
    }

    public String getPublico() {
        return publico;
    }

    public void setPublico(String publico) {
        this.publico = publico;
    }

    public void setViceCampeao(String viceCampeao) {
        this.viceCampeao = viceCampeao;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    @Override
    public String toString() {
        return "-------------------" + "\n" +
                "Copa do Mundo de Futebol de " + ano + "\n" +
                "País-Sede: " + paisSede + "\n" +
                "Campeão: " + campeao + "\n" +
                "Vice-campeão: " + viceCampeao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CopaDoMundo that)) return false;

        return Objects.equals(ano, that.ano);
    }
}

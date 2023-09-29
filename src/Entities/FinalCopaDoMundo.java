package Entities;
import  jakarta.persistence.*;

@Entity
@Table (name = "WorldCupMatches")
public class FinalCopaDoMundo {
    @Id
    @Column(name = "MatchID")
    private Long id;
    @Column(name = "Datetime")
    private String data;
    @Column(name = "Stadium")
    private String estadio;
    @Column(name = "Away_Team_Name")
    private String timeVisitante;
    @Column(name = "Home_Team_Name")
    private String timeMandante;
    @Column(name = "Win_conditions")
    private String observacoesVitoria;
    @Column (name = "Home_Team_Goals")
    private int golsMandante;
    @Column (name = "Stage")
    private String fase;
    @Column (name = "City")
    private String cidade;
    @Column (name = "Away_Team_Goals")
    private int golsVisitante;
    @Column (name = "Referee")
    private String arbitro;
    @Column (name = "Home_Team_Initials")
    private String abreviacaoMandante;
    @Column (name = "Away_Team_Initials")
    private String abreviacaoVisitante;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Year_Cup")
    private CopaDoMundo copaDoMundo;


    public FinalCopaDoMundo() {
    }

    public FinalCopaDoMundo(String abreviacaoMandante, CopaDoMundo copaDoMundo) {
        this.abreviacaoMandante = abreviacaoMandante;
        this.copaDoMundo = copaDoMundo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public String getTimeVisitante() {
        return timeVisitante;
    }

    public void setTimeVisitante(String timeVisitante) {
        this.timeVisitante = timeVisitante;
    }

    public String getTimeMandante() {
        return timeMandante;
    }

    public void setTimeMandante(String timeMandante) {
        this.timeMandante = timeMandante;
    }

    public String getObservacoesVitoria() {
        return observacoesVitoria;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getArbitro() {
        return arbitro;
    }

    public void setArbitro(String arbitro) {
        this.arbitro = arbitro;
    }

    public String getAbreviacaoMandante() {
        return abreviacaoMandante;
    }

    public void setAbreviacaoMandante(String abreviacaoMandante) {
        this.abreviacaoMandante = abreviacaoMandante;
    }

    public String getAbreviacaoVisitante() {
        return abreviacaoVisitante;
    }

    public void setAbreviacaoVisitante(String abreviacaoVisitante) {
        this.abreviacaoVisitante = abreviacaoVisitante;
    }

    public CopaDoMundo getCopaDoMundo() {
        return copaDoMundo;
    }

    public void setCopaDoMundo(CopaDoMundo copaDoMundo) {
        this.copaDoMundo = copaDoMundo;
    }

    public void setObservacoesVitoria(String observacoesVitoria) {
        this.observacoesVitoria = observacoesVitoria;
    }

    @Override
    public String toString() {
        return "-------------------" + "\n" +
                "Final da Copa do Mundo de Futebol de " + copaDoMundo.getAno() + "\n" +
                "País sede: " + copaDoMundo.getPaisSede() + "\n" +
                "Data: " + data + "\n" +
                "Estádio: " + estadio + "\n" +
                "Placar: " + timeMandante + " " + golsMandante + " X " + golsVisitante + " " + timeVisitante + "\n" +
                "Condições especiais: " + ((null != observacoesVitoria) ? observacoesVitoria : "Nada consta");
    }

    public int getGolsVisitante() {
        return golsVisitante;
    }

    public void setGolsVisitante(int golsVisitante) {
        this.golsVisitante = golsVisitante;
    }

    public int getGolsMandante() {
        return golsMandante;
    }

    public void setGolsMandante(int golsMandante) {
        this.golsMandante = golsMandante;
    }
}
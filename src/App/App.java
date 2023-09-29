package App;

import Dao.CopaDoMundoDAO;
import Dao.FinalCopaDoMundoDAO;
import Entities.CopaDoMundo;
import Entities.FinalCopaDoMundo;
import java.util.List;
import java.util.Optional;

//AUTORES: Maurício Tonin Verona, Pedro Henrique Brogliato

public class App {
    public static void main(String[] args) {
        //print all
        CopaDoMundoDAO copa = new CopaDoMundoDAO();
        imprimeLista(copa.buscaTodos());

        //print por ano
        FinalCopaDoMundoDAO partidaFinal = new FinalCopaDoMundoDAO();
        imprimeFinal(partidaFinal.buscaPorAno(new CopaDoMundo(2002)));

        //Inserir dados copa 2022
        CopaDoMundo copa2022 = criaCopa(2022, "Qatar", "Argentina", "France",
            "Croatia", "Morocco", 172, 32, 64, "3.404.252");
        try {
            if (copa.insereCopa(copa2022)) {
                System.out.println("Copa não inserida");
            } else {
                System.out.println("Copa inserida com sucesso:");
                System.out.println(copa2022);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //desempenho seleção
        desempenhoSelecao("Croatia");

        //Inserçao final
        FinalCopaDoMundo final2022 = criaFinalCopa("18 Dec 2022 - 18:00", "Lusail Stadium", "France", "Argentina",
                "Argentina win on penalties (4 - 2)", 3, "Final", "Lusail", 3, "MARCINIAK Szymon (POL)",
               "ARG", "FRA", copa2022);

        try {
            if (partidaFinal.insereFinalCopa(final2022)) {
                System.out.println("Final da copa não inserida");
            } else {
                System.out.println("Final da copa  inserida com sucesso:");
                System.out.println(final2022);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //busca infos copa
        FinalCopaDoMundoDAO copadoMundo = new FinalCopaDoMundoDAO();
        imprimeCopa(copadoMundo.buscaPorAno(new CopaDoMundo(1998)));
    }

    public static void imprimeFinal(Optional<List<FinalCopaDoMundo>> listaFinais) {
        for (FinalCopaDoMundo finalCopa : listaFinais.get()) {
            System.out.println(finalCopa);
        }
    }

    public static void imprimeCopa(Optional<List<FinalCopaDoMundo>> listaFinais) {
        for (FinalCopaDoMundo finalCopa : listaFinais.get()) {
            System.out.println("-------------------" + "\n" +
                    "Copa do Mundo de Futebol de " + finalCopa.getCopaDoMundo().getAno() + "\n" +
                    "País-sede: " + finalCopa.getCopaDoMundo().getPaisSede() + "\n" +
                    "Campeão: " + finalCopa.getCopaDoMundo().getCampeao() + "\n" +
                    "Vice-campeão: " + finalCopa.getCopaDoMundo().getViceCampeao() + "\n" +
                    "Data da final: " + finalCopa.getData() + "\n" +
                    "Estádio: " + finalCopa.getEstadio() + "\n" +
                    "Placar: " + finalCopa.getTimeMandante() + " " + finalCopa.getGolsMandante() + " X " +
                    finalCopa.getGolsVisitante() + " " + finalCopa.getTimeVisitante() + "\n" +
                    "OBS: " + ((null != finalCopa.getObservacoesVitoria()) ? finalCopa.getObservacoesVitoria() : "Nada consta"));
        }
    }

    public static void imprimeLista(Optional<List<CopaDoMundo>> lista) {
        System.out.println("Histórico das Copas do Mundo");
        for (CopaDoMundo copa : lista.get()) {
            System.out.println(copa);
        }
    }

    public static CopaDoMundo criaCopa(Integer ano, String pais, String campeao, String viceCampeao,
                                       String terceiroColocado, String quartoColocado, int gols, int times,
                                       int partidas, String publico)
    {
        CopaDoMundo copa = new CopaDoMundo();

        copa.setAno(ano);
        copa.setPaisSede(pais);
        copa.setCampeao(campeao);
        copa.setViceCampeao(viceCampeao);
        copa.setTerceiroColocado(terceiroColocado);
        copa.setQuartoColocado(quartoColocado);
        copa.setGolsMarcados(gols);
        copa.setQtTimes(times);
        copa.setQtPartidas(partidas);
        copa.setPublico(publico);
        return copa;
    }

    public static void desempenhoSelecao(String pais) {
        CopaDoMundoDAO copa = new CopaDoMundoDAO();
        List<CopaDoMundo> copas = copa.buscaPorPais(pais).get();
        System.out.println("-----------" + "Desempenho " + pais + "-----------");
        if(copas.isEmpty()) {
            System.out.println("O pais " + pais + " nunca esteve entre os quatro primeiros");
        } else {
            String colocacao;

            for(CopaDoMundo copaDoMundo : copas) {
                if(copaDoMundo.getCampeao().equals(pais)) {
                    colocacao = "Campeão";
                } else if (copaDoMundo.getViceCampeao().equals(pais)) {
                    colocacao = "Vice-campeão";
                } else if (copaDoMundo.getTerceiroColocado().equals(pais)) {
                    colocacao = "Terceiro colocado";
                } else {
                    colocacao = "Quarto colocado";
                }

                System.out.println("Edição da Copa: " + copaDoMundo.getAno() + "\n" +
                                   "País-sede: " + copaDoMundo.getPaisSede() + "\n" +
                                   "Colocação da seleção: " + colocacao + "\n" +
                                   "-------------------------------------------");
            }
        }
    }

    public static FinalCopaDoMundo criaFinalCopa(String data, String estadio, String timeVisitante,
                                                 String timeMandante, String observacoesVitoria, int golsMandante,
                                                 String fase, String cidade, int golsVisitante, String arbitro,
                                                 String abreviacaoMandante, String abreviacaoVisitante, CopaDoMundo copa)
    {
        FinalCopaDoMundoDAO finalCopaDAO = new FinalCopaDoMundoDAO();
        FinalCopaDoMundo finalCopa = new FinalCopaDoMundo();

        finalCopa.setId(finalCopaDAO.geraId());
        finalCopa.setData(data);
        finalCopa.setEstadio(estadio);
        finalCopa.setGolsMandante(golsMandante);
        finalCopa.setGolsVisitante(golsVisitante);
        finalCopa.setObservacoesVitoria(observacoesVitoria);
        finalCopa.setTimeMandante(timeMandante);
        finalCopa.setTimeVisitante(timeVisitante);
        finalCopa.setCopaDoMundo(copa);
        finalCopa.setArbitro(arbitro);
        finalCopa.setAbreviacaoMandante(abreviacaoMandante);
        finalCopa.setAbreviacaoVisitante(abreviacaoVisitante);
        finalCopa.setFase(fase);
        finalCopa.setCidade(cidade);

        return finalCopa;
    }
}
package school.sptech;

import school.sptech.exception.ArgumentoInvalidoException;
import school.sptech.exception.JogoInvalidoException;

import javax.swing.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.List;

public class Empresa {
    private String nome;
    private List<Jogo> jogos;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Jogo> getJogos() {
        return jogos;
    }

    public void adicionarJogo(Jogo jogo){
        if (jogo == null || (jogo.getCodigo() == null || jogo.getCodigo() == "") || (jogo.getNome() == null || jogo.getNome() == "") || (jogo.getGenero() == null || jogo.getGenero() == "") || (jogo.getPreco() == null || jogo.getPreco() <= 0) || (jogo.getAvaliacao() < 0 || jogo.getAvaliacao() > 5 ) || (jogo.getDataLancamento() == null || jogo.getDataLancamento().isAfter(LocalDate.now()))){
            throw new JogoInvalidoException();
        } else {
            jogos.add(jogo);
        }
    }

    public Jogo buscarJogoPorCodigo(String codigo){
        if (codigo == null || codigo.isEmpty()){
            throw new ArgumentoInvalidoException();
        }else {
            Jogo resposta = null;
            for (int i = 0; i < jogos.size(); i++) {
                Jogo jogo = jogos.get(i);
                if (jogo.getCodigo() == codigo) {
                    resposta = jogos.get(i);
                }
            }

            return resposta;
        }
    }

    public void removerJogoPorCodigo(String codigo){
        if (codigo == null || codigo == "" || codigo.isEmpty()){
            throw new ArgumentoInvalidoException();
        }
        for (int i = 0; i < jogos.size(); i++){
            Jogo jogo = jogos.get(i);
            if (jogo.getCodigo() == codigo){
                jogos.remove(i);
            }
        }
    }

    public Jogo buscarJogoComMelhorAvaliacao() {
        Jogo jogomaior = null;
        for (int i = 0; i < jogos.size(); i++) {
            Jogo jogo = jogos.get(i);
            jogomaior = jogo;
            if (jogo.getAvaliacao() > jogomaior.getAvaliacao()) {
                jogomaior = jogo;
            }
        }
        return jogomaior;
    }

    public List<Jogo> buscarJogoPorPeriodo(LocalDate dataInicio, LocalDate dataFim){
        List<Jogo> jogos = List.of();
        for (int i = 0; i < jogos.size(); i++){
            Jogo jogo = jogos.get(i);
            if (jogo.getDataLancamento().isAfter(dataInicio) || jogo.getDataLancamento().isBefore(dataFim)){
                jogos.add(jogo);
            }
        }
        return jogos;
    }
}

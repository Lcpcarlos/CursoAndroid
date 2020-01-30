package com.example.pokertentativafinal.DAO;

import android.util.Log;

import com.example.pokertentativafinal.model.Jogador;
import com.example.pokertentativafinal.model.JogadorDaEtapa;

import java.util.ArrayList;
import java.util.List;

public class JogadorDaEtapaDAO {
    public static final List<JogadorDaEtapa> jogadoresDaEtapa = new ArrayList<>();

    public static void salva(JogadorDaEtapa jogador) {
        jogadoresDaEtapa.add(jogador);
    }

    public static void limpa() {
        jogadoresDaEtapa.clear();
    }

    public List<JogadorDaEtapa> todos() {
        return new ArrayList<>(jogadoresDaEtapa);
    }

    public void edita(JogadorDaEtapa jogador) {
        JogadorDaEtapa jogadorEncontrado = buscaJogadorPeloID(jogador);
        if (jogadorEncontrado != null){
            int posicaoDoJogador = jogadoresDaEtapa.indexOf(jogadorEncontrado);
            jogadoresDaEtapa.set(posicaoDoJogador, jogador);

        }
    }

    private JogadorDaEtapa buscaJogadorPeloID(JogadorDaEtapa jogador) {
        for (JogadorDaEtapa a : jogadoresDaEtapa) {
            if (a.getId() == jogador.getId()) {
                return a;
            }
        }
        return null;
    }

}

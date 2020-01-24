package com.example.pokertentativafinal.DAO;

import com.example.pokertentativafinal.model.Jogador;

import java.util.ArrayList;
import java.util.List;

public class JogadorDAO {
    private static final List<Jogador> jogadores = new ArrayList<>();
    private static int contadorDeId = 1;

    public  void salva(Jogador jogador) {
        jogador.setId(contadorDeId);
        jogadores.add(jogador);
        atualizaID();
    }

    private  void atualizaID() {
        contadorDeId++;
    }

    public List<Jogador> todos() {
        return new ArrayList<>(jogadores);
    }

    public void edita(Jogador jogador) {
        Jogador jogadorEncontrado = buscaJogadorPeloID(jogador);
        if (jogadorEncontrado != null){
            int posicaoDoJogador = jogadores.indexOf(jogadorEncontrado);
            jogadores.set(posicaoDoJogador, jogador);

        }
    }

    private Jogador buscaJogadorPeloID(Jogador jogador) {
        for (Jogador a : jogadores) {
            if (a.getId() == jogador.getId()) {
                return a;
            }
        }
        return null;
    }

    public void remove(Jogador jogador) {
        Jogador jogadorEscolhido = buscaJogadorPeloID(jogador);
        if (jogadorEscolhido != null) {
           jogadores.remove(jogadorEscolhido);
        }
    }
}

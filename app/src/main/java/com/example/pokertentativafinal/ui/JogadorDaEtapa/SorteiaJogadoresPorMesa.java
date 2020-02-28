package com.example.pokertentativafinal.ui.JogadorDaEtapa;

import android.content.Context;

import com.example.pokertentativafinal.database.PokerDatabase;
import com.example.pokertentativafinal.database.dao.RoomJogadorDaEtapaDAO;
import com.example.pokertentativafinal.model.JogadorDaEtapa;

import java.util.List;
import java.util.Random;

public class SorteiaJogadoresPorMesa {
    private final Context context;
    private final RoomJogadorDaEtapaDAO jogadorDaEtapaDAO;
    private Object NullPointerException;
    private int resto;
    private int ttlMesa;
    private int mesaEscolhida;
    private int posicaoEscolhida;
    private  JogadorDaEtapa jogadorDaEtapa;
    private int[] ttlJogadoresPorMesa;
    private boolean continua = true;
    private int ttlJogadoresDefinido;
    private  int[][] mesaComPosicao;


    public SorteiaJogadoresPorMesa(Context context) {
        this.context = context;
        this.jogadorDaEtapaDAO = PokerDatabase
                .getInstance(context)
                .getRoomJogadorDaEtapaDAO();
    }

    public void carregaLista() {

        List<JogadorDaEtapa> todosJogadores = jogadorDaEtapaDAO.todos();

        encontraTotalDeMesas(todosJogadores);

        int[] ttlJogadoresPorMesa = defineTotalDeJogadoresPorMesa();


        colocaJogadoresNasMesas(todosJogadores, ttlJogadoresPorMesa);

    }


    private void colocaJogadoresNasMesas(List<JogadorDaEtapa> todosJogadores,
                                         int[] ttlJogadoresPorMesa){
//       --> carrega mesa com quantidade de posicoes ;
//       --> mesa possui maior numero de jogadores

        mesaComPosicao = new int[ttlMesa][ttlJogadoresPorMesa[0]];


        int[] jogadoresNaMesa = new int[ttlMesa];
        Random mesa = new Random();
        Random posicao = new Random();

        for (int i = 0; i < todosJogadores.size(); i++) {
            jogadorDaEtapa = todosJogadores.get(i);
//          Define mesa
            continua = true;
            while (continua) {
                mesaEscolhida = mesa.nextInt(ttlMesa);
                if (jogadoresNaMesa[mesaEscolhida] < ttlJogadoresPorMesa[mesaEscolhida]) {
                    jogadoresNaMesa[mesaEscolhida] = jogadoresNaMesa[mesaEscolhida] + 1;
                    continua = false;
                }
            }

//          Define posicao na mesa

            continua = true;
            while (continua){
                posicaoEscolhida = posicao.nextInt(mesaComPosicao[mesaEscolhida].length);

                if ((posicaoEscolhida + 1) <= ttlJogadoresPorMesa[mesaEscolhida]) {
                    if (mesaComPosicao[mesaEscolhida][posicaoEscolhida] == 0){
                        mesaComPosicao[mesaEscolhida][posicaoEscolhida] = 1;
                        continua = false;
                    }
                }
            }
            mesaEscolhida = mesaEscolhida + 1;
            posicaoEscolhida = posicaoEscolhida + 1;
            jogadorDaEtapa.setMesa(mesaEscolhida);
            jogadorDaEtapa.setPosicaoMesa(posicaoEscolhida);
            jogadorDaEtapaDAO.edita(jogadorDaEtapa);
        }
    }

    private int[] defineTotalDeJogadoresPorMesa() {
        int[] ttlJogadoresPorMesa = new int[ttlMesa];
        while (continua){
            for (int i = 0; i < ttlMesa ; i++) {
                ttlJogadoresPorMesa[i] = ttlJogadoresPorMesa[i] + 1;
                ttlJogadoresDefinido = ttlJogadoresDefinido + 1;
                if (ttlJogadoresDefinido == jogadorDaEtapaDAO.todos().size()){
                    continua = false;
                    break;
                }
            }
        }
        return ttlJogadoresPorMesa;
    }

    private void encontraTotalDeMesas(List<JogadorDaEtapa> todosJogadores) {
        resto = todosJogadores.size() % 9;

        if ( resto == 0  ) {
            ttlMesa = todosJogadores.size() / 9;
        } else {
            ttlMesa = (todosJogadores.size() / 9 ) + 1;
        }
    }
}


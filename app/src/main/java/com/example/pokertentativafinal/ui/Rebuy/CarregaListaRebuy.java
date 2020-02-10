package com.example.pokertentativafinal.ui.Rebuy;

import android.content.Context;

import com.example.pokertentativafinal.database.PokerDatabase;
import com.example.pokertentativafinal.database.dao.RoomJogadorDaEtapaDAO;
import com.example.pokertentativafinal.model.JogadorDaEtapa;

import java.util.List;

public class CarregaListaRebuy {
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


    public CarregaListaRebuy(Context context) {
        this.context = context;
        this.jogadorDaEtapaDAO = PokerDatabase
                .getInstance(context)
                .getRoomJogadorDaEtapaDAO();
    }

    public void carregaLista() {

        List<JogadorDaEtapa> todosJogadores = jogadorDaEtapaDAO.todosOrdemMesa();
    }
}


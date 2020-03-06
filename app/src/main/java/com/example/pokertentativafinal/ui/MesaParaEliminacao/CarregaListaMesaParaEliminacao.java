package com.example.pokertentativafinal.ui.MesaParaEliminacao;

import android.content.Context;

import com.example.pokertentativafinal.database.PokerDatabase;
import com.example.pokertentativafinal.database.dao.RoomJogadorDaEtapaDAO;
import com.example.pokertentativafinal.model.JogadorDaEtapa;

import java.util.List;

public class CarregaListaMesaParaEliminacao {
    private final Context context;
    private final RoomJogadorDaEtapaDAO jogadorDaEtapaDAO;
    private Object NullPointerException;
    private  JogadorDaEtapa jogadorDaEtapa;


    public CarregaListaMesaParaEliminacao(Context context) {
        this.context = context;
        this.jogadorDaEtapaDAO = PokerDatabase
                .getInstance(context)
                .getRoomJogadorDaEtapaDAO();
    }

    public void carregaLista() {

        List<JogadorDaEtapa> todosJogadores = jogadorDaEtapaDAO.todosOrdemMesaNaoEliminados();
    }
}


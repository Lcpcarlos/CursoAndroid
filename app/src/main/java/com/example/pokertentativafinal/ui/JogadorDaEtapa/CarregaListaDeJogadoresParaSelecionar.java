package com.example.pokertentativafinal.ui.JogadorDaEtapa;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.pokertentativafinal.DAO.JogadorDaEtapaDAO;
import com.example.pokertentativafinal.database.PokerDatabase;
import com.example.pokertentativafinal.database.dao.RoomJogadorDAO;
import com.example.pokertentativafinal.database.dao.RoomJogadorDaEtapaDAO;
import com.example.pokertentativafinal.model.Jogador;
import com.example.pokertentativafinal.model.JogadorDaEtapa;

import java.util.ArrayList;
import java.util.List;

public class CarregaListaDeJogadoresParaSelecionar {
    private final RoomJogadorDAO daoJogador;
    private final RoomJogadorDaEtapaDAO daoJogadorDaEtapa;
    private final Context context;
    private final JogadorDaEtapaDAO jogadorDaEtapaDAO;
    private Object NullPointerException;


    public CarregaListaDeJogadoresParaSelecionar(Context context) {
        this.context = context;
        this.daoJogador = PokerDatabase
                .getInstance(context)
                .getRoomJogadorDAO();

        this.daoJogadorDaEtapa = PokerDatabase
                .getInstance(context)
                .getRoomJogadorDaEtapaDAO();

        this.jogadorDaEtapaDAO = new JogadorDaEtapaDAO();
    }

    public void carregaLista() {

        jogadorDaEtapaDAO.limpa();

        List<Jogador> todosJogadores = daoJogador.todos();

        for (int i = 0; i < todosJogadores.size(); i++) {
            Jogador provisorio = todosJogadores.get(i);
//            JogadorDaEtapa jogadorNaEtapa = daoJogadorDaEtapa.jogadorEspecifico(provisorio.getId());

            jogadorDaEtapaDAO.salva(new JogadorDaEtapa(provisorio.getId(),
                    provisorio.getNome(), provisorio.isJogadorMarcadoParaEtapa()));
        }
    }
}


package com.example.pokertentativafinal.ui.MesaParaEliminacao;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.example.pokertentativafinal.DAO.JogadorDaEtapaDAO;
import com.example.pokertentativafinal.database.PokerDatabase;
import com.example.pokertentativafinal.database.dao.RoomJogadorDaEtapaDAO;
import com.example.pokertentativafinal.model.Jogador;
import com.example.pokertentativafinal.model.JogadorDaEtapa;
import com.example.pokertentativafinal.ui.MesaParaEliminacao.Adapter.ListaMesaParaEliminacaoAdapter;
import com.example.pokertentativafinal.ui.Rebuy.Adapter.ListaRebuyDaEtapaAdapter;

public class ListaMesaParaEliminacaoView {
    private final ListaMesaParaEliminacaoAdapter adapter;
    private final RoomJogadorDaEtapaDAO daoJogadorDaEtapa;
    private final Context context;
    private boolean eliminado = false;

    public ListaMesaParaEliminacaoView(Context context) {
        this.context = context;
        this.adapter = new ListaMesaParaEliminacaoAdapter(context);
        this.daoJogadorDaEtapa = PokerDatabase
                .getInstance(context)
                .getRoomJogadorDaEtapaDAO();
    }

    public void configuraAdapter(ListView listaJogador) {

        listaJogador.setAdapter(this.adapter);
    }

    public void atualizaLista() {

        adapter.atualiza(daoJogadorDaEtapa.todosOrdemMesaNaoEliminados());
    }



}

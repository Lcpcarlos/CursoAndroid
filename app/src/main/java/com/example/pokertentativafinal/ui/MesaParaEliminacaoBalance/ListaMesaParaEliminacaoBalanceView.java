package com.example.pokertentativafinal.ui.MesaParaEliminacaoBalance;

import android.content.Context;
import android.widget.ListView;

import com.example.pokertentativafinal.database.PokerDatabase;
import com.example.pokertentativafinal.database.dao.RoomJogadorDaEtapaDAO;
import com.example.pokertentativafinal.ui.MesaParaEliminacao.Adapter.ListaMesaParaEliminacaoAdapter;
import com.example.pokertentativafinal.ui.MesaParaEliminacaoBalance.Adapter.ListaMesaParaEliminacaoBalanceAdapter;

public class ListaMesaParaEliminacaoBalanceView {
    private final ListaMesaParaEliminacaoBalanceAdapter adapter;
    private final RoomJogadorDaEtapaDAO daoJogadorDaEtapa;
    private final Context context;
    private boolean eliminado = false;

    public ListaMesaParaEliminacaoBalanceView(Context context) {
        this.context = context;
        this.adapter = new ListaMesaParaEliminacaoBalanceAdapter(context);
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

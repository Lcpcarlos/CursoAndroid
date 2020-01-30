package com.example.pokertentativafinal.ui.Resultado;

import android.content.Context;
import android.widget.ListView;

import com.example.pokertentativafinal.database.PokerDatabase;
import com.example.pokertentativafinal.database.dao.RoomJogadorDaEtapaDAO;
import com.example.pokertentativafinal.model.JogadorDaEtapa;
import com.example.pokertentativafinal.ui.JogadoresDaEtapaSorteados.Adapter.ListaJogadoresDaEtapaPorMesaAdapter;
import com.example.pokertentativafinal.ui.Resultado.Adapter.ListaResultadoDaEtapaAdapter;

import java.util.List;

public class ListaResultadoDaEtapaView {
    private final ListaResultadoDaEtapaAdapter adapter;
    private final RoomJogadorDaEtapaDAO daoJogadorDaEtapa;
    private final Context context;

    public ListaResultadoDaEtapaView(Context context) {
        this.context = context;
        this.adapter = new ListaResultadoDaEtapaAdapter(context);
        this.daoJogadorDaEtapa = PokerDatabase
                .getInstance(context)
                .getRoomJogadorDaEtapaDAO();
    }

    public void configuraAdapter(ListView listaJogador) {

        listaJogador.setAdapter(this.adapter);
    }

    public void atualizaLista() {
        adapter.atualiza(daoJogadorDaEtapa.todosOrdemMesa());
    }

}

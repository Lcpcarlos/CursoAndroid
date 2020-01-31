package com.example.pokertentativafinal.ui.Mesas;

import android.content.Context;
import android.widget.ListView;

import com.example.pokertentativafinal.database.PokerDatabase;
import com.example.pokertentativafinal.database.dao.RoomJogadorDaEtapaDAO;
import com.example.pokertentativafinal.ui.JogadoresDaEtapaSorteados.Adapter.ListaJogadoresDaEtapaPorMesaAdapter;
import com.example.pokertentativafinal.ui.Mesas.Adapter.ListaMesaDaEtapaAdapter;

public class ListaMesaDaEtapaSorteadoView {
    private final ListaMesaDaEtapaAdapter adapter;
    private final RoomJogadorDaEtapaDAO daoJogadorDaEtapa;
    private final Context context;

    public ListaMesaDaEtapaSorteadoView(Context context) {
        this.context = context;
        this.adapter = new ListaMesaDaEtapaAdapter(context);
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

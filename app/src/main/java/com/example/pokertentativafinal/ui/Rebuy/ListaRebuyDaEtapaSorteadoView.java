package com.example.pokertentativafinal.ui.Rebuy;

import android.content.Context;
import android.widget.ListView;

import com.example.pokertentativafinal.database.PokerDatabase;
import com.example.pokertentativafinal.database.dao.RoomJogadorDaEtapaDAO;
import com.example.pokertentativafinal.ui.Rebuy.Adapter.ListaRebuyDaEtapaAdapter;

public class ListaRebuyDaEtapaSorteadoView {
    private final ListaRebuyDaEtapaAdapter adapter;
    private final RoomJogadorDaEtapaDAO daoJogadorDaEtapa;
    private final Context context;

    public ListaRebuyDaEtapaSorteadoView(Context context) {
        this.context = context;
        this.adapter = new ListaRebuyDaEtapaAdapter(context);
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

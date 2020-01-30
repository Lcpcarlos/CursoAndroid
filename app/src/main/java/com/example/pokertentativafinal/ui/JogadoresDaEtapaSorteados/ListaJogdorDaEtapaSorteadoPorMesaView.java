package com.example.pokertentativafinal.ui.JogadoresDaEtapaSorteados;

import android.content.Context;
import android.widget.ListView;

import com.example.pokertentativafinal.DAO.JogadorDaEtapaDAO;
import com.example.pokertentativafinal.database.PokerDatabase;
import com.example.pokertentativafinal.database.dao.RoomJogadorDaEtapaDAO;
import com.example.pokertentativafinal.ui.JogadorDaEtapa.Adapter.ListaJogadoresDaEtapaAdapter;
import com.example.pokertentativafinal.ui.JogadoresDaEtapaSorteados.Adapter.ListaJogadoresDaEtapaPorMesaAdapter;

public class ListaJogdorDaEtapaSorteadoPorMesaView {
    private final ListaJogadoresDaEtapaPorMesaAdapter adapter;
    private final RoomJogadorDaEtapaDAO daoJogadorDaEtapa;
    private final Context context;

    public ListaJogdorDaEtapaSorteadoPorMesaView(Context context) {
        this.context = context;
        this.adapter = new ListaJogadoresDaEtapaPorMesaAdapter(context);
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

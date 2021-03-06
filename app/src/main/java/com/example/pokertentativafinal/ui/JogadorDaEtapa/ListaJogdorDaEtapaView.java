package com.example.pokertentativafinal.ui.JogadorDaEtapa;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pokertentativafinal.DAO.JogadorDaEtapaDAO;
import com.example.pokertentativafinal.database.PokerDatabase;
import com.example.pokertentativafinal.database.dao.RoomJogadorDAO;
import com.example.pokertentativafinal.model.JogadorDaEtapa;
import com.example.pokertentativafinal.ui.JogadorDaEtapa.Adapter.ListaJogadoresDaEtapaAdapter;

import java.util.List;

public class ListaJogdorDaEtapaView {
    private final ListaJogadoresDaEtapaAdapter adapter;
    private final JogadorDaEtapaDAO daoJogadorDaEtapa;
    private final Context context;
    private int ttl = 0;

    public ListaJogdorDaEtapaView(Context context) {
        this.context = context;
        this.adapter = new ListaJogadoresDaEtapaAdapter(context);
        this.daoJogadorDaEtapa = new JogadorDaEtapaDAO();
    }

    public void configuraAdapter(ListView listaJogador) {

        listaJogador.setAdapter(this.adapter);
    }

    public void atualizaLista() {

        for (int i = 0; i < daoJogadorDaEtapa.todos().size(); i++) {
            if ( daoJogadorDaEtapa.todos().get(i).isCheck()) {
                ttl = ttl + 1;
            }

        }
        adapter.atualiza(daoJogadorDaEtapa.todos());
    }

}

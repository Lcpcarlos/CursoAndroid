package com.example.pokertentativafinal.ui.Mesas;

import android.content.Context;
import android.widget.ListView;

import com.example.pokertentativafinal.database.PokerDatabase;
import com.example.pokertentativafinal.database.dao.RoomJogadorDaEtapaDAO;
import com.example.pokertentativafinal.model.JogadorDaEtapa;
import com.example.pokertentativafinal.model.JogadorDaEtapaPorMesa;
import com.example.pokertentativafinal.ui.Mesas.Adapter.ListaMesaDaEtapaPorMesaAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListaJogadorDaEtapaMesaPorMesaView {
    private final ListaMesaDaEtapaPorMesaAdapter adapter;
    private final RoomJogadorDaEtapaDAO daoJogadorDaEtapa;
    private final Context context;

    public ListaJogadorDaEtapaMesaPorMesaView(Context context) {
        this.context = context;
        this.adapter = new ListaMesaDaEtapaPorMesaAdapter(context);
        this.daoJogadorDaEtapa = PokerDatabase
                .getInstance(context)
                .getRoomJogadorDaEtapaDAO();
    }

    public void configuraAdapter(ListView listaJogador) {

        listaJogador.setAdapter(this.adapter);
    }

    public void atualizaLista() {

        List<JogadorDaEtapaPorMesa> mesas = new ArrayList<>();
        List<JogadorDaEtapa> jogadores = daoJogadorDaEtapa.todosOrdemMesaNaoEliminados();
//        jogadores = daoJogadorDaEtapa.todosOrdemMesaNaoEliminados();
        List<String> nomes = new ArrayList<>();

        int mesaAtual = jogadores.get(0).getMesa();
        boolean mesaMudou = false;

        for (int i = 0; i < daoJogadorDaEtapa.todosOrdemMesaNaoEliminados().size(); i++) {
            if(mesaAtual != jogadores.get(i).getMesa()){
                mesaMudou = true;
            } else {
                mesaMudou = false;
            }

            if (mesaMudou) {
                mesas.add(new JogadorDaEtapaPorMesa(mesaAtual, new ArrayList<String>(nomes) ));
                mesaAtual = jogadores.get(i).getMesa();
                nomes.clear();
            }
            nomes.add(jogadores.get(i).getNome());
        }

        int mesa = jogadores.get(daoJogadorDaEtapa.todosOrdemMesaNaoEliminados().size() - 1).getMesa();
        mesas.add(new JogadorDaEtapaPorMesa(mesa, new ArrayList<String>(nomes)));



        adapter.atualiza(mesas);
    }

}

package com.example.pokertentativafinal.ui.JogadoresDaEtapaSorteados.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pokertentativafinal.R;
import com.example.pokertentativafinal.database.PokerDatabase;
import com.example.pokertentativafinal.database.dao.RoomJogadorDaEtapaDAO;
import com.example.pokertentativafinal.model.JogadorDaEtapa;

import java.util.ArrayList;
import java.util.List;

public class ListaJogadoresDaEtapaPorMesaAdapter extends BaseAdapter {
    private final List<JogadorDaEtapa> jogadoresDaEtapaSorteados = new ArrayList<>();
    private final Context context;
    private RoomJogadorDaEtapaDAO daoJogadordaEtapa;

    public ListaJogadoresDaEtapaPorMesaAdapter(Context context) {
        this.context = context;
        this.daoJogadordaEtapa = PokerDatabase
                .getInstance(context)
                .getRoomJogadorDaEtapaDAO();
    }

    @Override
    public int getCount() {
        return jogadoresDaEtapaSorteados.size();
    }

    @Override
    public JogadorDaEtapa getItem(int position) {
        return jogadoresDaEtapaSorteados.get(position);
    }

    @Override
    public long getItemId(int position) {
        return jogadoresDaEtapaSorteados.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolderMesaDaEtapa holder;
        final View viewCriada;

        if (convertView == null){
            viewCriada = LayoutInflater
                    .from(context)
                    .inflate(R.layout.layout_item_jogadordaetapa_sorteados
                            , parent, false);

            holder = new ViewHolderMesaDaEtapa(viewCriada);
            viewCriada.setTag(holder);

           } else {
            viewCriada = convertView;
            holder =    (ViewHolderMesaDaEtapa)
            viewCriada.getTag();
        }

        final JogadorDaEtapa jogadorDevolvido =  getItem(position);
        holder.nomeJogador.setText(jogadorDevolvido.getNome());
        holder.mesaJogador.setText(String.valueOf(jogadorDevolvido.getMesa()));
        holder.posicaoMesaJogador.setText(String.valueOf(jogadorDevolvido.getPosicaoMesa()));
        return viewCriada;
    }

    public void atualiza(List<JogadorDaEtapa> jogadores){
        this.jogadoresDaEtapaSorteados.clear();
        this.jogadoresDaEtapaSorteados.addAll(jogadores);
         notifyDataSetChanged();
    }

    public void remove(JogadorDaEtapa jogador) {
        jogadoresDaEtapaSorteados.remove(jogador);
        notifyDataSetChanged();
    }
}


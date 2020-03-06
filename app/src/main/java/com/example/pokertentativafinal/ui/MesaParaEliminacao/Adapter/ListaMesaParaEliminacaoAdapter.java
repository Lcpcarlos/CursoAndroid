package com.example.pokertentativafinal.ui.MesaParaEliminacao.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.pokertentativafinal.R;
import com.example.pokertentativafinal.database.PokerDatabase;
import com.example.pokertentativafinal.database.dao.RoomJogadorDaEtapaDAO;
import com.example.pokertentativafinal.model.JogadorDaEtapa;

import java.util.ArrayList;
import java.util.List;

public class ListaMesaParaEliminacaoAdapter extends BaseAdapter {
    private final List<JogadorDaEtapa> jogadoresDaEtapaSorteados = new ArrayList<>();
    private final Context context;
    private RoomJogadorDaEtapaDAO daoJogadordaEtapa;

    public ListaMesaParaEliminacaoAdapter(Context context) {
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

        ViewHolderMesaParaEliminacaoDaEtapa holder;
        final View viewCriada;

        if (convertView == null){
            viewCriada = LayoutInflater
                    .from(context)
                    .inflate(R.layout.layout_item_mesaeliminacao
                            , parent, false);

            holder = new ViewHolderMesaParaEliminacaoDaEtapa(viewCriada);
            viewCriada.setTag(holder);

           } else {
            viewCriada = convertView;
            holder =    (ViewHolderMesaParaEliminacaoDaEtapa)
            viewCriada.getTag();
        }

        final JogadorDaEtapa jogadorDevolvido =  getItem(position);
        holder.nomeJogador.setText(jogadorDevolvido.getNome());
        holder.posicaoMesa.setText(jogadorDevolvido.getMesa() + "-" + jogadorDevolvido.getPosicaoMesa());

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


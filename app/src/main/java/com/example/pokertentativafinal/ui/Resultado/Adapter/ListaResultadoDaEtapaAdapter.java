package com.example.pokertentativafinal.ui.Resultado.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.pokertentativafinal.R;
import com.example.pokertentativafinal.database.PokerDatabase;
import com.example.pokertentativafinal.database.dao.RoomJogadorDaEtapaDAO;
import com.example.pokertentativafinal.model.JogadorDaEtapa;

import java.util.ArrayList;
import java.util.List;

public class ListaResultadoDaEtapaAdapter extends BaseAdapter {
    private final List<JogadorDaEtapa> resultadoDaEtapa = new ArrayList<>();
    private final Context context;
    private RoomJogadorDaEtapaDAO daoJogadordaEtapa;

    public ListaResultadoDaEtapaAdapter(Context context) {
        this.context = context;
        this.daoJogadordaEtapa = PokerDatabase
                .getInstance(context)
                .getRoomJogadorDaEtapaDAO();
    }

    @Override
    public int getCount() {
        return resultadoDaEtapa.size();
    }

    @Override
    public JogadorDaEtapa getItem(int position) {
        return resultadoDaEtapa.get(position);
    }

    @Override
    public long getItemId(int position) {
        return resultadoDaEtapa.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolderResultadoDaEtapa holder;
        final View viewCriada;

        if (convertView == null){
            viewCriada = LayoutInflater
                    .from(context)
                    .inflate(R.layout.layout_item_resultadodaetapa
                            , parent, false);
            holder = new ViewHolderResultadoDaEtapa(viewCriada);
            viewCriada.setTag(holder);

           } else {
            viewCriada = convertView;
            holder =    (ViewHolderResultadoDaEtapa)
            viewCriada.getTag();
        }

        final JogadorDaEtapa jogadorDevolvido =  getItem(position);
        holder.nomeJogador.setText(jogadorDevolvido.getNome());

        holder.rebuyJogador.setText(String.valueOf(jogadorDevolvido.getTtlPagar()));

        return viewCriada;
    }

    public void atualiza(List<JogadorDaEtapa> jogadores){
        this.resultadoDaEtapa.clear();
        this.resultadoDaEtapa.addAll(jogadores);
         notifyDataSetChanged();
    }
}


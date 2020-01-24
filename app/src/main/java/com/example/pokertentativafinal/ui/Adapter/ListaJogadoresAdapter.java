package com.example.pokertentativafinal.ui.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.pokertentativafinal.R;
import com.example.pokertentativafinal.model.Jogador;

import java.util.ArrayList;
import java.util.List;

public class ListaJogadoresAdapter extends BaseAdapter {
    private final List<Jogador> jogadores = new ArrayList<>();
    private final Context context;

    public ListaJogadoresAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return jogadores.size();
    }

    @Override
    public Jogador getItem(int position) {
        return jogadores.get(position);
    }

    @Override
    public long getItemId(int position) {
        return jogadores.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolderJogador holder;
        View viewCriada;

        if (convertView == null){
            viewCriada = LayoutInflater
                    .from(context)
                    .inflate(R.layout.layout_item_jogador, parent, false);

            holder = new ViewHolderJogador(viewCriada);
            viewCriada.setTag(holder);

           } else {
            viewCriada = convertView;
            holder =    (ViewHolderJogador)
            viewCriada.getTag();
        }
        Jogador jogadorDevolvido =  getItem(position);
        holder.nomeJogadorDevolvido.setText(jogadorDevolvido.getNome());
        holder.telefoneJogadorDevolvido.setText(jogadorDevolvido.getTelefone());
        return viewCriada;
    }


    public void atualiza(List<Jogador> jogadores){
        this.jogadores.clear();
        this.jogadores.addAll(jogadores);
        notifyDataSetChanged();

    }

    public void remove(Jogador jogador) {
        jogadores.remove(jogador);
        notifyDataSetChanged();
    }
}


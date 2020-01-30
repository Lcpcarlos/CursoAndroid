package com.example.pokertentativafinal.ui.JogadorDaEtapa.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.pokertentativafinal.R;
import com.example.pokertentativafinal.model.Jogador;
import com.example.pokertentativafinal.model.JogadorDaEtapa;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

public class ListaJogadoresDaEtapaAdapter extends BaseAdapter {
    private final List<JogadorDaEtapa> jogadoresDaEtapa = new ArrayList<>();
    private final Context context;
    private boolean isSelected;

    public ListaJogadoresDaEtapaAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return jogadoresDaEtapa.size();
    }

    @Override
    public JogadorDaEtapa getItem(int position) {
        return jogadoresDaEtapa.get(position);
    }

    @Override
    public long getItemId(int position) {
        return jogadoresDaEtapa.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolderJogadorDaEtapa holder;
        final View viewCriada;

        if (convertView == null){
            viewCriada = LayoutInflater
                    .from(context)
                    .inflate(R.layout.layout_item_jogadordaetapa, parent, false);

            holder = new ViewHolderJogadorDaEtapa(viewCriada);
            viewCriada.setTag(holder);

           } else {
            viewCriada = convertView;
            holder =    (ViewHolderJogadorDaEtapa)
            viewCriada.getTag();
        }
        final JogadorDaEtapa jogadorDevolvido =  getItem(position);
        holder.nomeJogadorDevolvido.setText(jogadorDevolvido.getNome());
        holder.checkBoxJogador.setChecked(jogadorDevolvido.isCheck());
        holder.checkBoxJogador.setTag(jogadorDevolvido);
        holder.checkBoxJogador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBox = (CheckBox) v;
                JogadorDaEtapa j = (JogadorDaEtapa) v.getTag();
                j.setCheck(((CheckBox) v).isChecked());
                if(checkBox.isChecked()){
                    j.setCheck(true);
                } else {
                    j.setCheck(false);
                }
            }
        });
        return viewCriada;
    }


    public void atualiza(List<JogadorDaEtapa> jogadores){
        this.jogadoresDaEtapa.clear();
        this.jogadoresDaEtapa.addAll(jogadores);
         notifyDataSetChanged();

    }

    public void remove(JogadorDaEtapa jogador) {
        jogadoresDaEtapa.remove(jogador);
        notifyDataSetChanged();
    }
}


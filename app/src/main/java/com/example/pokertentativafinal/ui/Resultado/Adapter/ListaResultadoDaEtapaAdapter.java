package com.example.pokertentativafinal.ui.Resultado.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

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

        ViewHolderMesaDaEtapa holder;
        final View viewCriada;

        if (convertView == null){
            viewCriada = LayoutInflater
                    .from(context)
                    .inflate(R.layout.layout_item_resultadodaetapa
                            , parent, false);
            holder = new ViewHolderMesaDaEtapa(viewCriada);
            viewCriada.setTag(holder);

           } else {
            viewCriada = convertView;
            holder =    (ViewHolderMesaDaEtapa)
            viewCriada.getTag();
        }

        final JogadorDaEtapa jogadorResultado =  getItem(position);
        holder.checkBoxJogador.setChecked(jogadorResultado.isPagou());
        holder.nomeJogador.setText(jogadorResultado.getNome());

        holder.rebuyJogador.setText(String.valueOf(jogadorResultado.getTtlPagar()));

        holder.checkBoxJogador.setTag(jogadorResultado);
        holder.checkBoxJogador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBox = (CheckBox) v;
                JogadorDaEtapa j = (JogadorDaEtapa) v.getTag();
                j.setCheck(((CheckBox) v).isChecked());
                if(checkBox.isChecked()){
                    j.setPagou(true);
                } else {
                    j.setPagou(false);
                }
                daoJogadordaEtapa.edita(jogadorResultado);
            }
        });

        return viewCriada;
    }

    public void atualiza(List<JogadorDaEtapa> jogadores){
        this.resultadoDaEtapa.clear();
        this.resultadoDaEtapa.addAll(jogadores);
         notifyDataSetChanged();
    }
}


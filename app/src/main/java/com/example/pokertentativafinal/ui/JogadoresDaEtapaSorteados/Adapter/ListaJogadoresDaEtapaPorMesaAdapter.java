package com.example.pokertentativafinal.ui.JogadoresDaEtapaSorteados.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
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

        ViewHolderJogadorDaEtapaPorMesa holder;
        final View viewCriada;

        if (convertView == null){
            viewCriada = LayoutInflater
                    .from(context)
                    .inflate(R.layout.layout_item_jogadordaetapa_sorteados
                            , parent, false);

            holder = new ViewHolderJogadorDaEtapaPorMesa(viewCriada);
            viewCriada.setTag(holder);

           } else {
            viewCriada = convertView;
            holder =    (ViewHolderJogadorDaEtapaPorMesa)
            viewCriada.getTag();
        }

        final JogadorDaEtapa jogadorDevolvido =  getItem(position);
        holder.nomeJogador.setText(jogadorDevolvido.getNome());
        holder.mesaJogador.setText(String.valueOf(jogadorDevolvido.getMesa()));
        holder.posicaoMesaJogador.setText(String.valueOf(jogadorDevolvido.getPosicaoMesa()));
        holder.rebuyJogador.setText(String.valueOf(jogadorDevolvido.getQtReBuy()));
        holder.rebuyJogador.setTag(jogadorDevolvido);

        holder.rebuyJogador.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (!hasFocus){
                    TextView rebuy = (TextView) v;

                    JogadorDaEtapa g = (JogadorDaEtapa) v.getTag();
                    int numero;

                    String testeSeNumerico = rebuy.getText().toString();

                    if ( testeSeNumerico.isEmpty()) {
                        numero = 0;
                    } else {
                        numero = Integer.parseInt(rebuy.getText().toString());
                    }
                    g.setQtReBuy(numero);
                    daoJogadordaEtapa.edita(g);
                }
            }
        });

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


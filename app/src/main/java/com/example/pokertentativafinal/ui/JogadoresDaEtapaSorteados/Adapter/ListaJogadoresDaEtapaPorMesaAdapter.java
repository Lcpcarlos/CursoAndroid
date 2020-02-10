package com.example.pokertentativafinal.ui.JogadoresDaEtapaSorteados.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.pokertentativafinal.R;
import com.example.pokertentativafinal.database.PokerDatabase;
import com.example.pokertentativafinal.database.dao.RoomJogadorDaEtapaDAO;
import com.example.pokertentativafinal.model.JogadorDaEtapaPorMesa;

import java.util.ArrayList;
import java.util.List;

public class ListaJogadoresDaEtapaPorMesaAdapter extends BaseAdapter {
    private final List<JogadorDaEtapaPorMesa> jogadoresDaEtapaSorteados = new ArrayList<>();
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
    public JogadorDaEtapaPorMesa getItem(int position) {
        return jogadoresDaEtapaSorteados.get(position);
    }

    @Override
    public long getItemId(int position) {
        return jogadoresDaEtapaSorteados.get(position).getMesa();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolderMesaDaEtapaMesa holder;
        final View viewCriada;

        if (convertView == null) {
            viewCriada = LayoutInflater
                    .from(context)
                    .inflate(R.layout.layout_item_jogadordaetapa_sorteados
                            , parent, false);

            holder = new ViewHolderMesaDaEtapaMesa(viewCriada);
            viewCriada.setTag(holder);

        } else {
            viewCriada = convertView;
            holder = (ViewHolderMesaDaEtapaMesa)
                    viewCriada.getTag();
        }

        final JogadorDaEtapaPorMesa jogadorDevolvido = getItem(position);
        holder.posicaoJogador1.setText(" ");
        holder.posicaoJogador2.setText(" ");
        holder.posicaoJogador3.setText(" ");
        holder.posicaoJogador4.setText(" ");
        holder.posicaoJogador5.setText(" ");
        holder.posicaoJogador6.setText(" ");
        holder.posicaoJogador7.setText(" ");
        holder.posicaoJogador8.setText(" ");
        holder.posicaoJogador9.setText(" ");

        List<String> nomeJogador = jogadorDevolvido.getNomesJogadores();
        holder.mesa.setText("Mesa " + String.valueOf(jogadorDevolvido.getMesa()));

        if (nomeJogador.size() > 0) {

            holder.posicaoJogador1.setText(" 1 - " + nomeJogador.get(0));
        }
        if (nomeJogador.size() > 1) {
            holder.posicaoJogador2.setText(" 2 - " + nomeJogador.get(1));
        }
        if (nomeJogador.size() > 2) {

            holder.posicaoJogador3.setText(" 3 - " + nomeJogador.get(2));
        }
        if (nomeJogador.size() > 3) {

            holder.posicaoJogador4.setText(" 4 - " + nomeJogador.get(3));
        }
        if (nomeJogador.size() > 4) {

            holder.posicaoJogador5.setText(" 5 - " + nomeJogador.get(4));
        }
        if (nomeJogador.size() > 5) {

            holder.posicaoJogador6.setText(" 6 - " + nomeJogador.get(5));
        }
        if (nomeJogador.size() > 6) {

            holder.posicaoJogador7.setText(" 7 - " + nomeJogador.get(6));
        }
        if (nomeJogador.size() > 7) {

            holder.posicaoJogador8.setText(" 8 - " + nomeJogador.get(7));
        }
        if (nomeJogador.size() > 8) {

            holder.posicaoJogador9.setText(" 9 - " + nomeJogador.get(8));
        }
        return viewCriada;
    }

    public void atualiza(List<JogadorDaEtapaPorMesa> jogadores) {
        this.jogadoresDaEtapaSorteados.clear();
        this.jogadoresDaEtapaSorteados.addAll(jogadores);
        notifyDataSetChanged();
    }

}


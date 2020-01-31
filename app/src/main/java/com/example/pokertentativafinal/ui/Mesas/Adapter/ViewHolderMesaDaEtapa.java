package com.example.pokertentativafinal.ui.Mesas.Adapter;

import android.view.View;
import android.widget.TextView;

import com.example.pokertentativafinal.R;

class ViewHolderMesaDaEtapa {
    final TextView nomeJogador;
    final TextView mesaJogador;
    final TextView posicaoMesaJogador;
    final TextView rebuyJogador;


    public ViewHolderMesaDaEtapa(View view){
        nomeJogador =  view.findViewById(R.id.item_nome_mesadaetapa_sorteado);
        mesaJogador =  view.findViewById(R.id.item_mesa_mesadaetapa_sorteado);
        posicaoMesaJogador =  view.findViewById(R.id.item_posicaomesa_mesadaetapa_sorteado);
        rebuyJogador =  view.findViewById(R.id.item_rebuy_mesadaetapa_sorteado);
    }

}

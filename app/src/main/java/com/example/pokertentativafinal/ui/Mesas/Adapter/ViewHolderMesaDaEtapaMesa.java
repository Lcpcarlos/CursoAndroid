package com.example.pokertentativafinal.ui.Mesas.Adapter;

import android.view.View;
import android.widget.TextView;

import com.example.pokertentativafinal.R;

class ViewHolderMesaDaEtapaMesa {
    final TextView mesa;
    final TextView posicaoJogador1;
    final TextView posicaoJogador2;
    final TextView posicaoJogador3;
    final TextView posicaoJogador4;
    final TextView posicaoJogador5;
    final TextView posicaoJogador6;
    final TextView posicaoJogador7;
    final TextView posicaoJogador8;
    final TextView posicaoJogador9;


    public ViewHolderMesaDaEtapaMesa(View view){
        mesa =  view.findViewById(R.id.item_mesa);
        posicaoJogador1 =  view.findViewById(R.id.item_jogador_posicao_1);
        posicaoJogador2 =  view.findViewById(R.id.item_jogador_posicao_2);
        posicaoJogador3 =  view.findViewById(R.id.item_jogador_posicao_3);
        posicaoJogador4 =  view.findViewById(R.id.item_jogador_posicao_4);
        posicaoJogador5 =  view.findViewById(R.id.item_jogador_posicao_5);
        posicaoJogador6 =  view.findViewById(R.id.item_jogador_posicao_6);
        posicaoJogador7 =  view.findViewById(R.id.item_jogador_posicao_7);
        posicaoJogador8 =  view.findViewById(R.id.item_jogador_posicao_8);
        posicaoJogador9 =  view.findViewById(R.id.item_jogador_posicao_9);
    }

}

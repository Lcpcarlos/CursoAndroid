package com.example.pokertentativafinal.ui.Jogador.Adapter;

import android.view.View;
import android.widget.TextView;

import com.example.pokertentativafinal.R;

class ViewHolderJogador {
    final TextView nomeJogadorDevolvido;
    final TextView telefoneJogadorDevolvido;

    public ViewHolderJogador(View view){
        nomeJogadorDevolvido =  view.findViewById(R.id.item_nome_jogador);
        telefoneJogadorDevolvido =  view.findViewById(R.id.item_telefone_jogador);

    }

}

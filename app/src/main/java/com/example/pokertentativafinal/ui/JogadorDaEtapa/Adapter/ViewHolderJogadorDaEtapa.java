package com.example.pokertentativafinal.ui.JogadorDaEtapa.Adapter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.pokertentativafinal.R;
import com.example.pokertentativafinal.ui.JogadorDaEtapa.MainListaJogadorDaEtapaActivity;

class ViewHolderJogadorDaEtapa {
    final TextView nomeJogadorDevolvido;
    CheckBox checkBoxJogador;


    public ViewHolderJogadorDaEtapa(View view) {
        nomeJogadorDevolvido = view.findViewById(R.id.item_nome_jogadordaetapa);
        checkBoxJogador = view.findViewById(R.id.item_jogador_checkBox);
    }

}

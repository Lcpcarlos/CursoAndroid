package com.example.pokertentativafinal.ui.JogadoresDaEtapaSorteados.Adapter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.pokertentativafinal.R;

class ViewHolderJogadorDaEtapaPorMesa {
    final TextView nomeJogador;
    final TextView mesaJogador;
    final TextView posicaoMesaJogador;
    final TextView rebuyJogador;


    public ViewHolderJogadorDaEtapaPorMesa(View view){
        nomeJogador =  view.findViewById(R.id.item_nome_jogadordaetapa_sorteado);
        mesaJogador =  view.findViewById(R.id.item_mesa_jogadordaetapa_sorteado);
        posicaoMesaJogador =  view.findViewById(R.id.item_posicaomesa_jogadordaetapa_sorteado);
        rebuyJogador =  view.findViewById(R.id.item_rebuy_jogadordaetapa_sorteado);
    }

}

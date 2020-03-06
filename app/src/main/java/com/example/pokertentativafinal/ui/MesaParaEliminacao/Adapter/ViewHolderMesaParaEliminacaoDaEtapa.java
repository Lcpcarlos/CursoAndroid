package com.example.pokertentativafinal.ui.MesaParaEliminacao.Adapter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pokertentativafinal.R;

class ViewHolderMesaParaEliminacaoDaEtapa {
    final TextView posicaoMesa;
    final TextView nomeJogador;


    public ViewHolderMesaParaEliminacaoDaEtapa(View view){
        posicaoMesa =  view.findViewById(R.id.item_mesaPosicao_mesadaetapa_sorteado);
        nomeJogador =  view.findViewById(R.id.item_nome_mesadaetapa_sorteado);
    }

}

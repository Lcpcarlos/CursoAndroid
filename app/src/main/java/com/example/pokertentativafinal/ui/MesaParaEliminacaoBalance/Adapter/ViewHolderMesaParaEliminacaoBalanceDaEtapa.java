package com.example.pokertentativafinal.ui.MesaParaEliminacaoBalance.Adapter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.pokertentativafinal.R;

class ViewHolderMesaParaEliminacaoBalanceDaEtapa {
    final TextView posicaoMesa;
    final TextView nomeJogador;
        final CheckBox checkBoxDealearJogador;


    public ViewHolderMesaParaEliminacaoBalanceDaEtapa(View view){
        posicaoMesa =  view.findViewById(R.id.item_mesaPosicao_mesadaetapa_balance);
        nomeJogador =  view.findViewById(R.id.item_nome_mesadaetapa_balance);
        checkBoxDealearJogador = view.findViewById(R.id.item_checkBox_dealear_mesadaetapa_balance);
    }

}

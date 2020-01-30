package com.example.pokertentativafinal.ui.Resultado.Adapter;

import android.view.View;
import android.widget.TextView;

import com.example.pokertentativafinal.R;

class ViewHolderResultadoDaEtapa {
    final TextView nomeJogador;
    final TextView rebuyJogador;


    public ViewHolderResultadoDaEtapa(View view){
        nomeJogador =  view.findViewById(R.id.item_nome_resultadodaetapa);
        rebuyJogador =  view.findViewById(R.id.item_valor_resultadodaetapa);
    }

}

package com.example.pokertentativafinal.ui.Resultado.Adapter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.pokertentativafinal.R;

class ViewHolderMesaDaEtapa {
    final TextView nomeJogador;
    final TextView rebuyJogador;
    final CheckBox checkBoxJogador;
    final TextView valorTotal;


    public ViewHolderMesaDaEtapa(View view){
        nomeJogador =  view.findViewById(R.id.item_nome_resultadodaetapa);
        rebuyJogador =  view.findViewById(R.id.item_valor_resultadodaetapa);
        checkBoxJogador =  view.findViewById(R.id.item_checkBox_resultadodaetapa);
        valorTotal =  view.findViewById(R.id.item_valor_total_resultadodaetapa);
    }

}

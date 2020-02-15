package com.example.pokertentativafinal.ui.Rebuy.Adapter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pokertentativafinal.R;

class ViewHolderRebuyDaEtapa {
    final TextView posicaoMesa;
    final TextView nomeJogador;
    final CheckBox checkBoxRaikeJogador;
    final CheckBox checkBoxAddonJogador;
    final EditText rebuyJogador;


    public ViewHolderRebuyDaEtapa(View view){
        posicaoMesa =  view.findViewById(R.id.item_mesaPosicao_mesadaetapa_sorteado);
        nomeJogador =  view.findViewById(R.id.item_nome_mesadaetapa_sorteado);
        checkBoxRaikeJogador =  view.findViewById(R.id.item_checkBox_raike_mesadaetapa_sorteado);
        checkBoxAddonJogador =  view.findViewById(R.id.item_checkBox_addon_mesadaetapa_sorteado);
        rebuyJogador =  view.findViewById(R.id.item_rebuy_mesadaetapa_sorteado);
    }

}

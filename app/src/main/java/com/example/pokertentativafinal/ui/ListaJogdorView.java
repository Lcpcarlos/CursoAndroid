package com.example.pokertentativafinal.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.example.pokertentativafinal.DAO.JogadorDAO;
import com.example.pokertentativafinal.model.Jogador;
import com.example.pokertentativafinal.ui.Adapter.ListaJogadoresAdapter;

public class ListaJogdorView {
    private final ListaJogadoresAdapter adapter;
    private final JogadorDAO daoJogador;
    private final Context context;

    public ListaJogdorView(Context context) {
        this.context = context;
        this.adapter = new ListaJogadoresAdapter(context);
        this.daoJogador = new JogadorDAO();
    }

    public void confirmaRemoverJogador(@NonNull final MenuItem item) {
        new AlertDialog.Builder(context)
                .setTitle("Remover o Jogador")
                .setMessage("Tem certeza")
                .setPositiveButton("sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AdapterView.AdapterContextMenuInfo menuInfo =
                                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                        Jogador jogadorEscolhido = adapter.getItem(menuInfo.position);
                        removeJogador(jogadorEscolhido);

                    }
                })
                .setNegativeButton("Náo", null)
                .show();
    }
    private void removeJogador(Jogador jogador) {
        daoJogador.remove(jogador);
        adapter.remove(jogador);
    }
    public void configuraAdapter(ListView listaJogador) {

        listaJogador.setAdapter(this.adapter);
    }
    public void atualizaLista() {
        adapter.atualiza(daoJogador.todos());
    }

}

package com.example.pokertentativafinal.ui.MesaParaEliminacaoBalance;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pokertentativafinal.R;
import com.example.pokertentativafinal.database.PokerDatabase;
import com.example.pokertentativafinal.database.dao.RoomJogadorDaEtapaDAO;
import com.example.pokertentativafinal.model.Jogador;
import com.example.pokertentativafinal.model.JogadorDaEtapa;

import java.util.List;

import static com.example.pokertentativafinal.ui.ConstantesActivities.CHAVE_JOGADOR;

public class MainBalanceAvisoActivity extends AppCompatActivity {

    public static final String TITTLE_APPBAR = "Aviso balance jogador";

    private RoomJogadorDaEtapaDAO daoJogadordaEtapa;

    private JogadorDaEtapa jogadorRecebido;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_balanceaviso);

        setTitle(TITTLE_APPBAR);

        daoJogadordaEtapa = PokerDatabase.getInstance(this)
                .getRoomJogadorDaEtapaDAO();


    }


    @Override
    protected void onResume() {
        super.onResume();
        carregaJogador();

    }


    private void carregaJogador() {
        Intent dadoJogador = getIntent();
        if (dadoJogador.hasExtra(CHAVE_JOGADOR)) {
            jogadorRecebido = (JogadorDaEtapa) dadoJogador.getSerializableExtra(CHAVE_JOGADOR);
            avisaJogadorBalanceado(jogadorRecebido);
        } else {
            avisaQuebraMesa();
        }

    }


    public void avisaJogadorBalanceado(final JogadorDaEtapa jogadorDaEtapa) {

        new AlertDialog.Builder(this)
                .setTitle("Movimentação jogador")
                .setMessage(jogadorDaEtapa.getNome() + " vai para mesa " + jogadorDaEtapa.getMesa()
                        + " posição " + jogadorDaEtapa.getPosicaoMesa())
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .show();
    }

    public void avisaQuebraMesa() {

        new AlertDialog.Builder(this)
                .setTitle("Quebra da última mesa")
                .setMessage("Última mesa quebrada")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .show();
    }
}


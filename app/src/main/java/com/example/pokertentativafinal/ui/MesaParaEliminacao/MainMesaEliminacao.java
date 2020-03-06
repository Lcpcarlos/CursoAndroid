package com.example.pokertentativafinal.ui.MesaParaEliminacao;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pokertentativafinal.R;
import com.example.pokertentativafinal.database.PokerDatabase;
import com.example.pokertentativafinal.database.dao.RoomJogadorDaEtapaDAO;
import com.example.pokertentativafinal.model.JogadorDaEtapa;
import com.example.pokertentativafinal.ui.MesaParaEliminacaoBalance.MainBalanceActivity;

import static com.example.pokertentativafinal.ui.ConstantesActivities.CHAVE_JOGADOR;

public class MainMesaEliminacao extends AppCompatActivity {

    public static final String TITLE_APPBAR = "Relação de jogadores";

    private ListaMesaParaEliminacaoView listaMesaParaEliminacaoView;
    private CarregaListaMesaParaEliminacao carregaListaMesaParaEliminacao;
    private RoomJogadorDaEtapaDAO daoJogadordaEtapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mesa_eliminacao);
        setTitle(TITLE_APPBAR);

        daoJogadordaEtapa = PokerDatabase.getInstance(this)
                .getRoomJogadorDaEtapaDAO();


        listaMesaParaEliminacaoView =
                new ListaMesaParaEliminacaoView(this);
        CarregaListaMesaParaEliminacao carregaListaMesaParaEliminacao =
                new CarregaListaMesaParaEliminacao(this);
        carregaListaMesaParaEliminacao.carregaLista();
        configuraListaJogadores();
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaMesaParaEliminacaoView.atualizaLista();
    }


    private void configuraListaJogadores() {
        ListView listaJogador = findViewById(R.id.activity_mesa_eliminacao);
        listaMesaParaEliminacaoView.configuraAdapter(listaJogador);
       configuraListenerDeClickPorItem(listaJogador);
        registerForContextMenu(listaJogador);

    }


    private void configuraListenerDeClickPorItem(ListView listaJogador) {
        listaJogador.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                JogadorDaEtapa jogadorEscolhido = (JogadorDaEtapa) adapterView.getItemAtPosition(position);
                confirmaEliminacaoJogador(jogadorEscolhido);
            }
        });
    }


    public void confirmaEliminacaoJogador(final JogadorDaEtapa jogadorDaEtapa) {

        new AlertDialog.Builder(this)
                .setTitle("Elimina  Jogador")
                .setMessage("Eliminar  " + jogadorDaEtapa.getNome())
                .setPositiveButton("sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        eliminarJogador(jogadorDaEtapa);
                        abreBalance(jogadorDaEtapa);
                    }

                })
                .setNegativeButton("Náo", null)
                .show();
    }


    private void eliminarJogador(JogadorDaEtapa jogador) {
        int posicaoEliminacao = daoJogadordaEtapa.ttlJogadoresParaSeremEliminados();
        jogador.setPosicaoDeEliminacao(posicaoEliminacao);
        Log.i("jogadores", "eliminarJogador: " + posicaoEliminacao);
        daoJogadordaEtapa.edita(jogador);
    }

    private void abreBalance(JogadorDaEtapa jogador) {
        Intent vaParaBalance =
                new Intent(this,
                        MainBalanceActivity.class);
        vaParaBalance.putExtra(CHAVE_JOGADOR, jogador);
        startActivity(vaParaBalance);
    }
}

package com.example.pokertentativafinal.ui.JogadorDaEtapa;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pokertentativafinal.DAO.JogadorDAO;
import com.example.pokertentativafinal.DAO.JogadorDaEtapaDAO;
import com.example.pokertentativafinal.R;
import com.example.pokertentativafinal.database.PokerDatabase;
import com.example.pokertentativafinal.database.dao.RoomJogadorDaEtapaDAO;
import com.example.pokertentativafinal.model.Jogador;
import com.example.pokertentativafinal.model.JogadorDaEtapa;
import com.example.pokertentativafinal.ui.JogadorDaEtapa.Adapter.ListaJogadoresDaEtapaAdapter;
import com.example.pokertentativafinal.ui.JogadoresDaEtapaSorteados.MainJogadoresDaEtapaSorteados;
import com.example.pokertentativafinal.ui.Mesas.MainMesas;
import com.example.pokertentativafinal.ui.Resultado.MainResultadoDaEtapaActivity;

import java.util.List;

public class MainListaJogadorDaEtapaActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR = "Lista de Jogadores para Etapa";

    private final JogadorDAO daoJogador = new JogadorDAO();
    private ListaJogadoresDaEtapaAdapter adapter;
    private ListaJogdorDaEtapaView listaJogdorDaEtapaView;
    private CarregaListaDeJogadoresParaSelecionar carregaListaDeJogadoresParaSelecionar;
    private RoomJogadorDaEtapaDAO daoJogadordaEtapa;
    private JogadorDaEtapaDAO jogadorDaEtapaDAO;
    private JogadorDaEtapa jogadorSelecionado;
    private boolean temJogadorSelecionado = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lista_jogadordaetapa);

        daoJogadordaEtapa = PokerDatabase.getInstance(this)
                .getRoomJogadorDaEtapaDAO();


        setTitle(TITULO_APPBAR);
        listaJogdorDaEtapaView = new ListaJogdorDaEtapaView(this);
        carregaListaDeJogadoresParaSelecionar =
                new CarregaListaDeJogadoresParaSelecionar(this);
        carregaListaDeJogadoresParaSelecionar.carregaLista();
        configuraListaJogadores();
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaJogdorDaEtapaView.atualizaLista();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_jogador_da_etapa_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int itemIdMenu = item.getItemId();
        if (itemIdMenu == R.id.activity_jogador_da_etapa_menu_sorteio) {
                final Intent vaparaSorteio = new Intent(this, MainJogadoresDaEtapaSorteados.class);
                if (daoJogadordaEtapa.todos().size() > 0) {
                    temJogadorSelecionado = false;
                    verificaTemJogadorSelecionado();
                    if (temJogadorSelecionado){
                         perguntaSeQuerRefazerOSorteio(vaparaSorteio);
                    } else {
                        avisaQueNaoTemJogadorParaSorteio();
                    }

                } else {
                  temJogadorSelecionado = false;
                  verificaTemJogadorSelecionado();
                    if (temJogadorSelecionado){
                        perguntaSeQuerEfetuarOSorteio(vaparaSorteio);
                    } else {
                        avisaQueNaoTemJogadorParaSorteio();
                    }
                }
        }

        if (itemIdMenu == R.id.activity_jogador_da_etapa_menu_mesa) {
            startActivity( new Intent(this, MainMesas.class));
        }
        return super.onOptionsItemSelected(item);
    }

    private void avisaQueNaoTemJogadorParaSorteio() {
        new AlertDialog.Builder(this)
                .setTitle("Efetuar Sorteio")
                .setMessage("Náo existem jogadores salvos na etapa.")
                .setNegativeButton("ok", null)
                .show();
    }


    private void perguntaSeQuerRefazerOSorteio(final Intent vaparaSorteio) {
        new AlertDialog.Builder(this)
          .setTitle("Efetuar Sorteio")
          .setMessage("Sorteio já efetuado. Refazer o sorteio?")
          .setPositiveButton("sim", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {
                  salvarJogadorNaEtapa();
                  startActivity(vaparaSorteio);
              }
          })
          .setNegativeButton("Náo", null)
          .show();
    }

    private void perguntaSeQuerEfetuarOSorteio(final Intent vaparaSorteio) {
        new AlertDialog.Builder(this)
          .setTitle("Efetuar Sorteio")
          .setMessage("Tem certeza?")
          .setPositiveButton("sim", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {
                  salvarJogadorNaEtapa();
                  startActivity(vaparaSorteio);
              }
          })
          .setNegativeButton("Náo", null)
          .show();
    }

    private void salvarJogadorNaEtapa() {
        JogadorDaEtapaDAO jogadorDaEtapaDAO = new JogadorDaEtapaDAO();
        JogadorDaEtapa jogadorDaEtapaSelecionado = new JogadorDaEtapa();

        for (int i = 0; i < jogadorDaEtapaDAO.todos().size() ; i++) {
            jogadorDaEtapaSelecionado = jogadorDaEtapaDAO.todos().get(i);
            if (jogadorDaEtapaDAO.todos().get(i).isCheck()) {
               daoJogadordaEtapa.remove(jogadorDaEtapaSelecionado);
               daoJogadordaEtapa.salva(jogadorDaEtapaSelecionado);
            } else {
               daoJogadordaEtapa.remove(jogadorDaEtapaSelecionado);
            }
        }
    }

    private void verificaTemJogadorSelecionado() {
        JogadorDaEtapaDAO jogadores = new JogadorDaEtapaDAO();
        for (int i = 0; i < jogadores.todos().size() ; i++) {
            if (jogadores.todos().get(i).isCheck()) {
               temJogadorSelecionado = true;
            }
        }
    }


    private void configuraListaJogadores() {
        ListView listaJogador = findViewById(R.id.activity_main_lista_de_jogadordaetapa);
        listaJogdorDaEtapaView.configuraAdapter(listaJogador);
    }

}

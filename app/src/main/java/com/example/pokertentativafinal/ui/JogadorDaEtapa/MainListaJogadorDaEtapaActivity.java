package com.example.pokertentativafinal.ui.JogadorDaEtapa;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pokertentativafinal.DAO.JogadorDAO;
import com.example.pokertentativafinal.DAO.JogadorDaEtapaDAO;
import com.example.pokertentativafinal.R;
import com.example.pokertentativafinal.database.PokerDatabase;
import com.example.pokertentativafinal.database.dao.RoomJogadorDaEtapaDAO;
import com.example.pokertentativafinal.model.JogadorDaEtapa;
import com.example.pokertentativafinal.ui.JogadorDaEtapa.Adapter.ListaJogadoresDaEtapaAdapter;
import com.example.pokertentativafinal.ui.JogadoresDaEtapaSorteados.MainJogadoresDaEtapaSorteados;
import com.example.pokertentativafinal.ui.Mesas.MainMesas;
import com.example.pokertentativafinal.ui.Resultado.MainResultadoDaEtapaActivity;

public class MainListaJogadorDaEtapaActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR = "Lista de Jogadores para Etapa";

    private final JogadorDAO daoJogador = new JogadorDAO();
    private ListaJogadoresDaEtapaAdapter adapter;
    private ListaJogdorDaEtapaView listaJogdorDaEtapaView;
    private CarregaListaDeJogadoresParaSelecionar carregaListaDeJogadoresParaSelecionar;
    private RoomJogadorDaEtapaDAO daoJogadordaEtapa;
    private JogadorDaEtapa jogadorSelecionado;


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
        if (itemIdMenu == R.id.activity_jogador_da_etapa_menu) {
            salvarJogadorNaEtapa();
        }

        if (itemIdMenu == R.id.activity_jogador_da_etapa_menu_sorteio) {
            startActivity( new Intent(this, MainJogadoresDaEtapaSorteados.class));
        }
        if (itemIdMenu == R.id.activity_jogador_da_etapa_menu_mesa) {
            startActivity( new Intent(this, MainMesas.class));
        }
        return super.onOptionsItemSelected(item);
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


    private void configuraListaJogadores() {
        ListView listaJogador = findViewById(R.id.activity_main_lista_de_jogadordaetapa);
        listaJogdorDaEtapaView.configuraAdapter(listaJogador);
    }

}

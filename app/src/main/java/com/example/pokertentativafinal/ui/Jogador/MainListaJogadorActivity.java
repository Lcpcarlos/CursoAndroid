package com.example.pokertentativafinal.ui.Jogador;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pokertentativafinal.DAO.JogadorDAO;
import com.example.pokertentativafinal.R;
import com.example.pokertentativafinal.model.Jogador;
import com.example.pokertentativafinal.ui.Jogador.Adapter.ListaJogadoresAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.example.pokertentativafinal.ui.ConstantesActivities.CHAVE_JOGADOR;

public class MainListaJogadorActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR = "Lista de Jogadores";

    private final JogadorDAO daoJogador = new JogadorDAO();
    private ListaJogadoresAdapter adapter;
    private ListaJogdorView listaJogdorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lista_jogador);

        setTitle(TITULO_APPBAR);
        listaJogdorView = new ListaJogdorView(this);
        configuraFABNovoJogador();
        configuraListaJogadores();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_jogadores_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itemIdMenu = item.getItemId();
        if (itemIdMenu == R.id.Activity_lista_jogador_menu_remover) {
            listaJogdorView.confirmaRemoverJogador(item);
        }
        return super.onContextItemSelected(item);
    }

    private void configuraFABNovoJogador() {
        FloatingActionButton btnNovoJogador =
                findViewById(R.id.Activity_lista_jogador_FAB_novo_jogador);

        btnNovoJogador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abreFormularioInsereJogador();
            }
        });
    }

    private void abreFormularioInsereJogador() {
        startActivity(new Intent(this, ActivityFormularioJogador.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaJogdorView.atualizaLista();
    }


    private void configuraListaJogadores() {
        ListView listaJogador = findViewById(R.id.activity_main_lista_de_jogador);

        listaJogdorView.configuraAdapter(listaJogador);
        configuraListenerDeClickPorItem(listaJogador);
        registerForContextMenu(listaJogador);
    }

    private void configuraListenerDeClickPorItem(ListView listaJogador) {
        listaJogador.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Jogador jogadorEscolhido = (Jogador) adapterView.getItemAtPosition(position);
                abreFormularioEditaJogador(jogadorEscolhido);
            }
        });
    }

    private void abreFormularioEditaJogador(Jogador jogador) {
        Intent vaParaJogadorEscolhido =
                new Intent(MainListaJogadorActivity.this,
                        ActivityFormularioJogador.class);
        vaParaJogadorEscolhido.putExtra(CHAVE_JOGADOR, jogador);
        startActivity(vaParaJogadorEscolhido);
    }
}

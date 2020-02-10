package com.example.pokertentativafinal.ui.Rebuy;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pokertentativafinal.R;
import com.example.pokertentativafinal.database.PokerDatabase;
import com.example.pokertentativafinal.database.dao.RoomJogadorDaEtapaDAO;
import com.example.pokertentativafinal.ui.JogadoresDaEtapaSorteados.Adapter.ListaJogadoresDaEtapaPorMesaAdapter;
import com.example.pokertentativafinal.ui.Resultado.MainResultadoDaEtapaActivity;

public class MainRebuy extends AppCompatActivity {

    public static final String TITLE_APPBAR = "Jogadores sorteados";

    private ListaJogadoresDaEtapaPorMesaAdapter adapter;
    private ListaRebuyDaEtapaSorteadoView listaMesaDaEtapaSorteadoView;
    private CarregaListaRebuy CarregaListaRebuy;
    private RoomJogadorDaEtapaDAO daoJogadordaEtapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mesa);
        setTitle(TITLE_APPBAR);

        daoJogadordaEtapa = PokerDatabase.getInstance(this)
                .getRoomJogadorDaEtapaDAO();

        listaMesaDaEtapaSorteadoView =
                new ListaRebuyDaEtapaSorteadoView(this);
        CarregaListaRebuy carregaListaRebuy =
                new CarregaListaRebuy(this);
        carregaListaRebuy.carregaLista();
        configuraListaJogadores();
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaMesaDaEtapaSorteadoView.atualizaLista();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_jogador_sorteado_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemIdMenu = item.getItemId();
        if (itemIdMenu == R.id.activity_jogador_sorteado_menu) {
            startActivity( new Intent(this, MainResultadoDaEtapaActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }


    private void configuraListaJogadores() {
        ListView listaJogador = findViewById(R.id.activity_mesa_da_etapa_sorteados);
        listaMesaDaEtapaSorteadoView.configuraAdapter(listaJogador);

    }


}

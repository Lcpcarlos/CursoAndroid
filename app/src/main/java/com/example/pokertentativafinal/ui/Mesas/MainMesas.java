package com.example.pokertentativafinal.ui.Mesas;

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
import com.example.pokertentativafinal.ui.JogadoresDaEtapaSorteados.CarregaListaDeJogadoresPorMesa;
import com.example.pokertentativafinal.ui.JogadoresDaEtapaSorteados.ListaJogdorDaEtapaSorteadoPorMesaView;
import com.example.pokertentativafinal.ui.Resultado.MainResultadoDaEtapaActivity;

public class MainMesas extends AppCompatActivity {

    public static final String TITLE_APPBAR = "Jogadores sorteados";

    private ListaJogadoresDaEtapaPorMesaAdapter adapter;
    private ListaJogdorDaEtapaSorteadoPorMesaView  listaJogdorDaEtapaSorteadoPorMesaView;
    private CarregaListaMesa CarregaListaMesa;
    private RoomJogadorDaEtapaDAO daoJogadordaEtapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mesa);
        setTitle(TITLE_APPBAR);

        daoJogadordaEtapa = PokerDatabase.getInstance(this)
                .getRoomJogadorDaEtapaDAO();

        listaJogdorDaEtapaSorteadoPorMesaView =
                new ListaJogdorDaEtapaSorteadoPorMesaView(this);
        CarregaListaMesa carregaListaMesa =
                new CarregaListaMesa(this);
        carregaListaMesa.carregaLista();
        configuraListaJogadores();
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaJogdorDaEtapaSorteadoPorMesaView.atualizaLista();
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
        ListView listaJogador = findViewById(R.id.activity_jogadores_da_etapa_sorteados);
        listaJogdorDaEtapaSorteadoPorMesaView.configuraAdapter(listaJogador);

    }


}

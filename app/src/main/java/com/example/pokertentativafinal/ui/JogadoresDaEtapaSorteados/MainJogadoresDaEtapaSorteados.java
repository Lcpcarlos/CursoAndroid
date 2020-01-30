package com.example.pokertentativafinal.ui.JogadoresDaEtapaSorteados;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pokertentativafinal.DAO.JogadorDAO;
import com.example.pokertentativafinal.R;
import com.example.pokertentativafinal.database.PokerDatabase;
import com.example.pokertentativafinal.database.dao.RoomJogadorDaEtapaDAO;
import com.example.pokertentativafinal.model.Jogador;
import com.example.pokertentativafinal.model.JogadorDaEtapa;
import com.example.pokertentativafinal.ui.JogadorDaEtapa.Adapter.ListaJogadoresDaEtapaAdapter;
import com.example.pokertentativafinal.ui.JogadorDaEtapa.CarregaListaDeJogadoresParaSelecionar;
import com.example.pokertentativafinal.ui.JogadorDaEtapa.ListaJogdorDaEtapaView;
import com.example.pokertentativafinal.ui.JogadoresDaEtapaSorteados.Adapter.ListaJogadoresDaEtapaPorMesaAdapter;
import com.example.pokertentativafinal.ui.Resultado.MainResultadoDaEtapaActivity;

public class MainJogadoresDaEtapaSorteados extends AppCompatActivity {

    public static final String TITLE_APPBAR = "Jogadores sorteados";

    private ListaJogadoresDaEtapaPorMesaAdapter adapter;
    private ListaJogdorDaEtapaSorteadoPorMesaView  listaJogdorDaEtapaSorteadoPorMesaView;
    private CarregaListaDeJogadoresPorMesa carregaListaDeJogadoresPorMesa;
    private RoomJogadorDaEtapaDAO daoJogadordaEtapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogadores_da_etapa_sorteados);
        setTitle(TITLE_APPBAR);

        daoJogadordaEtapa = PokerDatabase.getInstance(this)
                .getRoomJogadorDaEtapaDAO();

        listaJogdorDaEtapaSorteadoPorMesaView =
                new ListaJogdorDaEtapaSorteadoPorMesaView(this);
        CarregaListaDeJogadoresPorMesa carregaListaDeJogadoresPorMesa =
                new CarregaListaDeJogadoresPorMesa(this);
        carregaListaDeJogadoresPorMesa.carregaLista();
        configuraListaJogadores();
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaJogdorDaEtapaSorteadoPorMesaView.atualizaLista();
    }



    private void configuraListaJogadores() {
        ListView listaJogador = findViewById(R.id.activity_jogadores_da_etapa_sorteados);
        listaJogdorDaEtapaSorteadoPorMesaView.configuraAdapter(listaJogador);

    }


}

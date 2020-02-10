package com.example.pokertentativafinal.ui.Mesas;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pokertentativafinal.R;
import com.example.pokertentativafinal.database.PokerDatabase;
import com.example.pokertentativafinal.database.dao.RoomJogadorDaEtapaDAO;
import com.example.pokertentativafinal.ui.JogadoresDaEtapaSorteados.Adapter.ListaJogadoresDaEtapaPorMesaAdapter;
import com.example.pokertentativafinal.ui.JogadoresDaEtapaSorteados.CarregaListaDeJogadoresPorMesa;
import com.example.pokertentativafinal.ui.JogadoresDaEtapaSorteados.ListaJogdorDaEtapaSorteadoPorMesaView;

public class MainMesa extends AppCompatActivity {

    public static final String TITLE_APPBAR = "Jogadores sorteados";

    private ListaJogadoresDaEtapaPorMesaAdapter adapter;
    private ListaJogadorDaEtapaMesaPorMesaView listaJogadorDaEtapaMesaPorMesaView;
    private RoomJogadorDaEtapaDAO daoJogadordaEtapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogadores_da_etapa_sorteados);
        setTitle(TITLE_APPBAR);

        daoJogadordaEtapa = PokerDatabase.getInstance(this)
                .getRoomJogadorDaEtapaDAO();

        listaJogadorDaEtapaMesaPorMesaView =
                new ListaJogadorDaEtapaMesaPorMesaView(this);
        configuraListaJogadores();
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaJogadorDaEtapaMesaPorMesaView.atualizaLista();
    }



    private void configuraListaJogadores() {
        ListView listaJogador = findViewById(R.id.activity_jogadores_da_etapa_sorteados);
        listaJogadorDaEtapaMesaPorMesaView.configuraAdapter(listaJogador);

    }


}

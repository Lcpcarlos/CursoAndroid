package com.example.pokertentativafinal.ui.Mesas;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pokertentativafinal.R;
import com.example.pokertentativafinal.database.PokerDatabase;
import com.example.pokertentativafinal.database.dao.RoomJogadorDaEtapaDAO;

public class MainMesa extends AppCompatActivity {

    public static final String TITLE_APPBAR = "Jogadores sorteados por mesa";

    private ListaJogadorDaEtapaMesaPorMesaView listaJogadorDaEtapaMesaPorMesaView;
    private RoomJogadorDaEtapaDAO daoJogadordaEtapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mesa);
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
        ListView listaJogador = findViewById(R.id.activity_list_main_mesa);
        listaJogadorDaEtapaMesaPorMesaView.configuraAdapter(listaJogador);

    }


}

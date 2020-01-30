package com.example.pokertentativafinal.ui.Resultado;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.pokertentativafinal.R;
import com.example.pokertentativafinal.database.PokerDatabase;
import com.example.pokertentativafinal.database.dao.RoomJogadorDaEtapaDAO;
import com.example.pokertentativafinal.ui.Resultado.Adapter.ListaResultadoDaEtapaAdapter;
import com.example.pokertentativafinal.ui.Resumo.MainResumoActivity;

public class MainResultadoDaEtapaActivity extends AppCompatActivity {

    public static final String TITTLE_APPBAR = "Resultado da Etapa";
    private ListaResultadoDaEtapaAdapter adapter;
    private ListaResultadoDaEtapaView  listaResultadoDaEtapaView;
    private CarregaListaDeResultado carregaListaDeResultado;
    private RoomJogadorDaEtapaDAO daoJogadordaEtapa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_resultado_da_etapa);

        setTitle(TITTLE_APPBAR);
        daoJogadordaEtapa = PokerDatabase.getInstance(this)
                .getRoomJogadorDaEtapaDAO();

        listaResultadoDaEtapaView =
                new ListaResultadoDaEtapaView(this);
        CarregaListaDeResultado carregaListaDeResultado =
                new CarregaListaDeResultado(this);
        carregaListaDeResultado.carregaLista();
       configuraListaJogadores();
    }


    @Override
    protected void onResume() {
        super.onResume();
        listaResultadoDaEtapaView.atualizaLista();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_resultado_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemIdMenu = item.getItemId();
        if (itemIdMenu == R.id.activity_resultado_menu) {
            startActivity( new Intent(this, MainResumoActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }


    private void configuraListaJogadores() {
        ListView listaJogador = findViewById(R.id.activity_main_resultado_da_etapa);
        listaResultadoDaEtapaView.configuraAdapter(listaJogador);

    }
}

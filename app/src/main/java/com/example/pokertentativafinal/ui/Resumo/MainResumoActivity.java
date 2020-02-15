package com.example.pokertentativafinal.ui.Resumo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pokertentativafinal.R;
import com.example.pokertentativafinal.database.PokerDatabase;
import com.example.pokertentativafinal.database.dao.RoomJogadorDaEtapaDAO;
import com.example.pokertentativafinal.model.JogadorDaEtapa;
import com.example.pokertentativafinal.ui.Resultado.CarregaListaDeResultado;

import java.util.List;

public class MainResumoActivity extends AppCompatActivity {

    public static final String TITTLE_APPBAR = "Resumo da Etapa";

    private CarregaListaDeResultado carregaListaDeResultado;
    private RoomJogadorDaEtapaDAO daoJogadordaEtapa;
    private TextView total;
    private TextView totalJogadores;
    private TextView ttlRaike;
    private TextView ttlMesaFinal;
    private TextView ttlReserva;
    private TextView ttlPremio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_resumo);

        setTitle(TITTLE_APPBAR);

        daoJogadordaEtapa = PokerDatabase.getInstance(this)
                .getRoomJogadorDaEtapaDAO();

        carregaResumo();
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaResumo();
    }

    private void carregaResumo() {
        List<JogadorDaEtapa> jogadores = daoJogadordaEtapa.todos();
        int qtJogadores = 0;
        double totalGeral = 0;
        double totalRaike = 0;
        double totalMesaFinal = 0;
        double totalReserva = 0;
        double totalPremio = 0;
        for (int i = 0; i < jogadores.size(); i++) {
            totalGeral = totalGeral + jogadores.get(i).getTtlPagar();
            totalRaike = totalRaike + 5;
        }
//          encontra valor multiplo de 5 para 10% do total
        int valor = 0;
        valor = (int) ((totalGeral - totalRaike) * 10 / 100);
        valor = valor / 5;
        valor = valor * 5;

        totalMesaFinal = valor;

        totalReserva = totalMesaFinal;

        totalPremio = totalGeral - totalRaike - totalMesaFinal - totalReserva;

        qtJogadores =  jogadores.size();
        totalJogadores = findViewById(R.id.activity_resumo_da_etapa_totaljogadores);
        totalJogadores.setText(String.valueOf(qtJogadores));

        total = findViewById(R.id.activity_resumo_da_etapa_total);
        total.setText(String.valueOf(totalGeral));

        ttlRaike = findViewById(R.id.activity_resumo_da_etapa_ttlraike);
        ttlRaike.setText(String.valueOf(totalRaike));

        ttlRaike = findViewById(R.id.activity_resumo_da_etapa_mesafinal);
        ttlRaike.setText(String.valueOf(totalMesaFinal));

        ttlRaike = findViewById(R.id.activity_resumo_da_etapa_reserva);
        ttlRaike.setText(String.valueOf(totalReserva));

        ttlRaike = findViewById(R.id.activity_resumo_da_etapa_premio);
        ttlRaike.setText(String.valueOf(totalPremio));

        int ttlJogadores = jogadores.size();
        int qtdeJogadorPodio = 2;
        int acrescentaPodio = 0;
        acrescentaPodio = ttlJogadores / 9;

        qtdeJogadorPodio = qtdeJogadorPodio + acrescentaPodio;

        ttlRaike = findViewById(R.id.activity_podio_da_etapa_premio);
        ttlRaike.setText(String.valueOf(qtdeJogadorPodio));


    }
}

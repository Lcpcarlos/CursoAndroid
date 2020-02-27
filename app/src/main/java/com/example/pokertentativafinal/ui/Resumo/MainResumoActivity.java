package com.example.pokertentativafinal.ui.Resumo;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pokertentativafinal.R;
import com.example.pokertentativafinal.database.PokerDatabase;
import com.example.pokertentativafinal.database.dao.RoomJogadorDaEtapaDAO;
import com.example.pokertentativafinal.model.JogadorDaEtapa;
import com.example.pokertentativafinal.ui.Resultado.CarregaListaDeResultado;

import java.util.List;

public class MainResumoActivity extends AppCompatActivity {

    public static final String TITTLE_APPBAR = "Resumo da Etapa";
    public static final double dezPorCento = 0.1;

    private CarregaListaDeResultado carregaListaDeResultado;
    private RoomJogadorDaEtapaDAO daoJogadordaEtapa;
    private TextView total;
    private TextView totalJogadores;
    private TextView ttlRaike;
    private TextView ttlMesaFinal;
    private TextView ttlReserva;
    private TextView ttlPremio;
    private TextView ttlJogadorPodio;
    private List<JogadorDaEtapa> jogadores = daoJogadordaEtapa.todos();
    private int qtJogadores = 0;
    private double totalGeral = 0;
    private double totalRaike = 0;
    private double totalMesaFinal = 0;
    private double totalReserva = 0;
    private double totalPremio = 0;

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

        somaTotalPagarTotalRaike();

        int valorDezPorcento = calculaValorDezPorcento();

        totalMesaFinal = valorDezPorcento;

        totalReserva = totalMesaFinal;

        totalPremio = totalGeral - totalRaike - totalMesaFinal - totalReserva;

        configuraTotalJogadores(jogadores);

        configuraValorTotaldaEtapa(totalGeral);

        configuraValorTotalRaike(totalRaike);

        configuraValorTotalMesaFinal(totalMesaFinal);

        configuraValorReserva(totalReserva);

        configuraTotalValorPremio(totalPremio);

        configuraTotalJogadoresPodio(jogadores);

    }

    private int calculaValorDezPorcento() {
        //          encontra valorDezPorcento multiplo de 5 para 10% do total
        int valorDezPorcento = 0;
        valorDezPorcento = (int) ((totalGeral - totalRaike) * dezPorCento );
        valorDezPorcento = valorDezPorcento / 5;
        valorDezPorcento = valorDezPorcento * 5;
        return valorDezPorcento;
    }

    private void somaTotalPagarTotalRaike() {
        for (int i = 0; i < jogadores.size(); i++) {
            totalGeral = totalGeral + jogadores.get(i).getTtlPagar();

            if (jogadores.get(i).isRaike()) {
                totalRaike = totalRaike + 5;
            }
        }
    }

    private void configuraTotalJogadoresPodio(List<JogadorDaEtapa> jogadores) {
        int ttlJogadores = jogadores.size();
        int qtdeJogadorPodio = 2;
        int acrescentaPodio = 0;
        acrescentaPodio = ttlJogadores / 9;

        qtdeJogadorPodio = qtdeJogadorPodio + acrescentaPodio;

        ttlJogadorPodio = findViewById(R.id.activity_podio_da_etapa_premio);
        ttlJogadorPodio.setText(String.valueOf(qtdeJogadorPodio));
    }

    private void configuraTotalValorPremio(double totalPremio) {
        ttlPremio = findViewById(R.id.activity_resumo_da_etapa_premio);
        ttlPremio.setText(String.valueOf(totalPremio));
    }

    private void configuraValorReserva(double totalReserva) {
        ttlReserva = findViewById(R.id.activity_resumo_da_etapa_reserva);
        ttlReserva.setText(String.valueOf(totalReserva));
    }

    public void configuraValorTotalMesaFinal(double totalMesaFinal) {
        ttlMesaFinal = findViewById(R.id.activity_resumo_da_etapa_mesafinal);
        ttlMesaFinal.setText(String.valueOf(totalMesaFinal));
    }

    public void configuraValorTotalRaike(double totalRaike) {
        ttlRaike = findViewById(R.id.activity_resumo_da_etapa_ttlraike);
        ttlRaike.setText(String.valueOf(totalRaike));
    }

    private void configuraValorTotaldaEtapa(double totalGeral) {
        total = findViewById(R.id.activity_resumo_da_etapa_total);
        total.setText(String.valueOf(totalGeral));
    }

    private void configuraTotalJogadores(List<JogadorDaEtapa> jogadores) {
        int qtJogadores;
        qtJogadores = jogadores.size();
        totalJogadores = findViewById(R.id.activity_resumo_da_etapa_totaljogadores);
        totalJogadores.setText(String.valueOf(qtJogadores));
    }
}


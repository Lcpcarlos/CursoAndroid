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

import static java.lang.Math.round;

public class MainResumoActivity extends AppCompatActivity {

    public static final String TITTLE_APPBAR = "Resumo da Etapa";
    public static final double dezPorCento = 0.1;
    public static final int VALORBUYIN = 5;
    public static final double SETENTAPORCENTO = 0.70;
    public static final double CINQUENTAESETEPORCENTO = 0.57;
    public static final double CINQUENTAEDOISPORCENTO = 0.52;
    public static final double QUARENTAEOITOPORCENTO = 0.48;
    public static final double QUARENTAECINCOPORCENTO = 0.45;
    public static final double QUARENTAEDOISPORCENTO = 0.42;
    public static final double VINTENOVEPORCENTO = 0.29;
    public static final double VINTESEISPORCENTO = 0.26;
    public static final double VINTEQUATROPORCENTO = 0.24;
    public static final double VINTEEDOISPORCENTO = 0.22;
    public static final double VINTEPORCENTO = 0.20;
    public static final double QUARTOZEPORCENTO = 0.14;
    public static final double TREZEPORCENTO = 0.13;
    public static final double NOVEPORCENTO = 0.09;
    public static final double OITOPORCENTO = 0.08;
    public static final double SETEPORCENTO = 0.07;
    public static final double CINCOPORCENTO = 0.05;

    private CarregaListaDeResultado carregaListaDeResultado;
    private RoomJogadorDaEtapaDAO daoJogadordaEtapa;
    private TextView total;
    private TextView totalJogadores;
    private TextView totalRebuy;
    private TextView ttlRaike;
    private TextView ttlMesaFinal;
    private TextView ttlReserva;
    private TextView ttlPremio;
    private TextView ttlJogadorPodio;
    private TextView primeiroColocado;
    private TextView segundoColocado;
    private TextView terceiroColocado;
    private TextView quartoColocado;
    private TextView quintoColocado;
    private TextView sextoColocado;
    private TextView setimoColocado;
    private List<JogadorDaEtapa> jogadores;
    private int qtJogadores = 0;
    private int qtRebuy = 0;
    private double totalGeral = 0;
    private double totalRaike = 0;
    private double totalMesaFinal = 0;
    private double totalReserva = 0;
    private double totalPremio = 0;
    private double valorPrimeiroColocado = 0;
    private double valorSegundoColocado = 0;
    private double valorTerceiroColocado = 0;
    private double valorQuartoColocado = 0;
    private double valorQuintoColocado = 0;
    private double valorSextoColocado = 0;
    private double valorSetimoColocado = 0;
    private int qtdeJogadorPodio = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_resumo);

        setTitle(TITTLE_APPBAR);

        daoJogadordaEtapa = PokerDatabase.getInstance(this)
                .getRoomJogadorDaEtapaDAO();
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaResumo();
    }


    private void carregaResumo() {
        jogadores = daoJogadordaEtapa.todos();

        somaTotalPagarTotalRaikeQtRebuy();

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

        configuraQtRebuy();

        if (qtdeJogadorPodio == 2) {
            valorPrimeiroColocado = round((totalPremio * SETENTAPORCENTO) / VALORBUYIN);
            valorPrimeiroColocado = valorPrimeiroColocado * VALORBUYIN;
            valorSegundoColocado = totalPremio - valorPrimeiroColocado;
        }

        if (qtdeJogadorPodio == 3) {
            valorPrimeiroColocado = round((totalPremio * CINQUENTAESETEPORCENTO) / VALORBUYIN);
            valorPrimeiroColocado = valorPrimeiroColocado * VALORBUYIN;
            valorSegundoColocado = round((totalPremio * VINTENOVEPORCENTO) / VALORBUYIN);
            valorSegundoColocado = valorSegundoColocado * VALORBUYIN;
            valorTerceiroColocado = totalPremio - valorPrimeiroColocado - valorSegundoColocado;
        }


        if (qtdeJogadorPodio == 4) {
            valorPrimeiroColocado = round((totalPremio * CINQUENTAEDOISPORCENTO) / VALORBUYIN);
            valorPrimeiroColocado = valorPrimeiroColocado * VALORBUYIN;
            valorSegundoColocado = round((totalPremio * VINTESEISPORCENTO) / VALORBUYIN);
            valorSegundoColocado = valorSegundoColocado * VALORBUYIN;
            valorTerceiroColocado = round((totalPremio * QUARTOZEPORCENTO) / VALORBUYIN);
            valorTerceiroColocado = valorTerceiroColocado * VALORBUYIN;
            valorQuartoColocado = totalPremio - valorPrimeiroColocado - valorSegundoColocado
                    - valorTerceiroColocado;
        }

        if (qtdeJogadorPodio == 5) {
            valorPrimeiroColocado = round((totalPremio * QUARENTAEOITOPORCENTO) / VALORBUYIN);
            valorPrimeiroColocado = valorPrimeiroColocado * VALORBUYIN;
            valorSegundoColocado = round((totalPremio * VINTEQUATROPORCENTO) / VALORBUYIN);
            valorSegundoColocado = valorSegundoColocado * VALORBUYIN;
            valorTerceiroColocado = round((totalPremio * QUARTOZEPORCENTO) / VALORBUYIN);
            valorTerceiroColocado = valorTerceiroColocado * VALORBUYIN;
            valorQuartoColocado = round((totalPremio * OITOPORCENTO) / VALORBUYIN);
            valorQuartoColocado = valorQuartoColocado * VALORBUYIN;

            valorQuintoColocado = totalPremio - valorPrimeiroColocado - valorSegundoColocado
                    - valorTerceiroColocado - valorQuartoColocado;
        }

        if (qtdeJogadorPodio == 6) {
            valorPrimeiroColocado = round((totalPremio * QUARENTAECINCOPORCENTO) / VALORBUYIN);
            valorPrimeiroColocado = valorPrimeiroColocado * VALORBUYIN;
            valorSegundoColocado = round((totalPremio * VINTEEDOISPORCENTO) / VALORBUYIN);
            valorSegundoColocado = valorSegundoColocado * VALORBUYIN;
            valorTerceiroColocado = round((totalPremio * TREZEPORCENTO) / VALORBUYIN);
            valorTerceiroColocado = valorTerceiroColocado * VALORBUYIN;
            valorQuartoColocado = round((totalPremio * NOVEPORCENTO) / VALORBUYIN);
            valorQuartoColocado = valorQuartoColocado * VALORBUYIN;
            valorQuintoColocado = round((totalPremio * SETEPORCENTO) / VALORBUYIN);
            valorQuintoColocado = valorQuintoColocado * VALORBUYIN;

            valorSextoColocado = totalPremio - valorPrimeiroColocado - valorSegundoColocado
                    - valorTerceiroColocado - valorQuartoColocado - valorQuintoColocado;
        }

        if (qtdeJogadorPodio == 7) {
            valorPrimeiroColocado = round((totalPremio * QUARENTAEDOISPORCENTO) / VALORBUYIN);
            valorPrimeiroColocado = valorPrimeiroColocado * VALORBUYIN;
            valorSegundoColocado = round((totalPremio * VINTEPORCENTO) / VALORBUYIN);
            valorSegundoColocado = valorSegundoColocado * VALORBUYIN;
            valorTerceiroColocado = round((totalPremio * TREZEPORCENTO) / VALORBUYIN);
            valorTerceiroColocado = valorTerceiroColocado * VALORBUYIN;
            valorQuartoColocado = round((totalPremio * NOVEPORCENTO) / VALORBUYIN);
            valorQuartoColocado = valorQuartoColocado * VALORBUYIN;
            valorQuintoColocado = round((totalPremio * SETEPORCENTO) / VALORBUYIN);
            valorQuintoColocado = valorQuintoColocado * VALORBUYIN;
            valorSextoColocado = round((totalPremio * CINCOPORCENTO) / VALORBUYIN);
            valorSextoColocado = valorSextoColocado * VALORBUYIN;

            valorSetimoColocado = totalPremio - valorPrimeiroColocado - valorSegundoColocado
                    - valorTerceiroColocado - valorQuartoColocado - valorQuintoColocado
                    - valorSextoColocado;
        }

        configuraPremiados1a7();


    }

    private void configuraPremiados1a7() {
        primeiroColocado = findViewById(R.id.activity_resumo_da_etapa_1);
        primeiroColocado.setText(String.valueOf(String.valueOf(valorPrimeiroColocado)));

        segundoColocado = findViewById(R.id.activity_resumo_da_etapa_2);
        segundoColocado.setText(String.valueOf(String.valueOf(valorSegundoColocado)));

        terceiroColocado = findViewById(R.id.activity_resumo_da_etapa_3);
        terceiroColocado.setText(String.valueOf(String.valueOf(valorTerceiroColocado)));

        quartoColocado = findViewById(R.id.activity_resumo_da_etapa_4);
        quartoColocado.setText(String.valueOf(String.valueOf(valorQuartoColocado)));

        quintoColocado = findViewById(R.id.activity_resumo_da_etapa_5);
        quintoColocado.setText(String.valueOf(String.valueOf(valorQuintoColocado)));

        sextoColocado = findViewById(R.id.activity_resumo_da_etapa_6);
        sextoColocado.setText(String.valueOf(String.valueOf(valorSextoColocado)));

        setimoColocado = findViewById(R.id.activity_resumo_da_etapa_7);
        setimoColocado.setText(String.valueOf(String.valueOf(valorSetimoColocado)));
    }

    private int calculaValorDezPorcento() {
        //          encontra valorDezPorcento multiplo de 5 para 10% do total
        int valorDezPorcento = 0;
        valorDezPorcento = (int) ((totalGeral - totalRaike) * dezPorCento);
        valorDezPorcento = valorDezPorcento / 5;
        valorDezPorcento = valorDezPorcento * 5;
        return valorDezPorcento;
    }

    private void somaTotalPagarTotalRaikeQtRebuy() {
        for (int i = 0; i < jogadores.size(); i++) {
            totalGeral = totalGeral + jogadores.get(i).getTtlPagar();

            if (jogadores.get(i).isRaike()) {
                totalRaike = totalRaike + 5;
            }

            qtRebuy = qtRebuy + jogadores.get(i).getQtReBuy();
        }
    }

    private void configuraTotalJogadoresPodio(List<JogadorDaEtapa> jogadores) {
        int ttlJogadores = jogadores.size();
        qtdeJogadorPodio = 2;
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
        qtJogadores = jogadores.size();
        totalJogadores = findViewById(R.id.activity_resumo_da_etapa_totaljogadores);
        totalJogadores.setText(String.valueOf(qtJogadores));
    }


    private void configuraQtRebuy() {
        totalRebuy = findViewById(R.id.activity_resumo_da_etapa_totalrebuy);
        totalRebuy.setText(String.valueOf(qtRebuy));
    }

}


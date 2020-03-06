package com.example.pokertentativafinal.ui.MesaParaEliminacaoBalance;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pokertentativafinal.R;
import com.example.pokertentativafinal.database.PokerDatabase;
import com.example.pokertentativafinal.database.dao.RoomJogadorDaEtapaDAO;
import com.example.pokertentativafinal.model.JogadorDaEtapa;

import java.util.List;

import static com.example.pokertentativafinal.ui.ConstantesActivities.CHAVE_JOGADOR;

public class MainBalanceActivity extends AppCompatActivity {

    public static final String TITTLE_APPBAR = "Selecionar o próximo DEALER";

    private RoomJogadorDaEtapaDAO daoJogadordaEtapa;
    private TextView titulo;

    private int ttlMesas = 0;
    private int[] ttlJogadoresPorMesa;
    private int ttlJogadoresMesaComMaisJogadores = 0;
    private int ttlJogadoresMesaComMenosJogadores = 99;
    private int mesaComMaisJogadores = 0;
    private int mesaComMenosJogadores = 0;
    private int novaPosicao = 1;
    private JogadorDaEtapa jogadorEscolhido;
    private ListaMesaParaEliminacaoBalanceView listaMesaParaEliminacaoBalanceView;
    private int ttlMesaDeveriaTer = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_balance);

        setTitle(TITTLE_APPBAR);

        daoJogadordaEtapa = PokerDatabase.getInstance(this)
                .getRoomJogadorDaEtapaDAO();


        carregaResumo();

        listaMesaParaEliminacaoBalanceView =
                new ListaMesaParaEliminacaoBalanceView(this);
        configuraListaJogadores();


    }

    private void balanceMesasQuebra() {
        int qtJogadores = daoJogadordaEtapa.ttlJogadoresParaSeremEliminados();
        int resto = qtJogadores % ttlMesaDeveriaTer;
        int qtJogadoresPorMesa = 0;

        if (resto == 0) {
            qtJogadoresPorMesa = qtJogadores / ttlMesaDeveriaTer;
        } else {
            qtJogadoresPorMesa = (qtJogadores / ttlMesaDeveriaTer) + 1;
        }

        int jogadorRecuperar = 3;
        int ttlJogadoresMesaQuebrar = daoJogadordaEtapa.ttlJogadoresPorMesa(ttlMesas);

        for (int i = 0; i < ttlJogadoresMesaQuebrar; i++) {
            JogadorDaEtapa jogadorDaEtapa = null;
            Boolean jogadorEncontrouMesa = false;


            jogadorDaEtapa = daoJogadordaEtapa.jogadorEspecificoPorPosicaoEMesa(ttlMesas, jogadorRecuperar);

            if (!jogadorEncontrouMesa) {

                if (ttlMesaDeveriaTer >= 1) {
                    int ttlJogadoresMesaUm = daoJogadordaEtapa.ttlJogadoresPorMesa(1);
                    int ttlJogadoresFaltanteMesaUm = qtJogadoresPorMesa - ttlJogadoresMesaUm;
                    if (ttlJogadoresFaltanteMesaUm > 0) {
                        jogadorDaEtapa.setMesa(1);
                        jogadorDaEtapa.setPosicaoMesa(ttlJogadoresMesaUm + 1);
                        daoJogadordaEtapa.edita(jogadorDaEtapa);
                        jogadorEncontrouMesa = true;
                    }
                }
            }


            if (!jogadorEncontrouMesa) {

                if (ttlMesaDeveriaTer >= 2) {
                    int ttlJogadoresMesadois = daoJogadordaEtapa.ttlJogadoresPorMesa(2);
                    int ttlJogadoresFaltanteMesadois = qtJogadoresPorMesa - ttlJogadoresMesadois;
                    if (ttlJogadoresFaltanteMesadois > 0) {
                        jogadorDaEtapa.setMesa(2);
                        jogadorDaEtapa.setPosicaoMesa(ttlJogadoresMesadois + 1);
                        daoJogadordaEtapa.edita(jogadorDaEtapa);
                        jogadorEncontrouMesa = true;
                    }
                }
            }

            if (!jogadorEncontrouMesa) {

                if (ttlMesaDeveriaTer >= 3) {
                    int ttlJogadoresMesatres = daoJogadordaEtapa.ttlJogadoresPorMesa(3);
                    int ttlJogadoresFaltanteMesatres = qtJogadoresPorMesa - ttlJogadoresMesatres;
                    if (ttlJogadoresFaltanteMesatres > 0) {
                        jogadorDaEtapa.setMesa(3);
                        jogadorDaEtapa.setPosicaoMesa(ttlJogadoresMesatres + 1);
                        daoJogadordaEtapa.edita(jogadorDaEtapa);
                        jogadorEncontrouMesa = true;
                    }
                }
            }


            if (!jogadorEncontrouMesa) {

                if (ttlMesaDeveriaTer >= 4) {
                    int ttlJogadoresMesaquatro = daoJogadordaEtapa.ttlJogadoresPorMesa(4);
                    int ttlJogadoresFaltanteMesaquatro = qtJogadoresPorMesa - ttlJogadoresMesaquatro;
                    if (ttlJogadoresFaltanteMesaquatro > 0) {
                        jogadorDaEtapa.setMesa(4);
                        jogadorDaEtapa.setPosicaoMesa(ttlJogadoresMesaquatro + 1);
                        daoJogadordaEtapa.edita(jogadorDaEtapa);
                        jogadorEncontrouMesa = true;
                    }
                }
            }
            if (!jogadorEncontrouMesa) {

                if (ttlMesaDeveriaTer >= 5) {
                    int ttlJogadoresMesaCinco = daoJogadordaEtapa.ttlJogadoresPorMesa(5);
                    int ttlJogadoresFaltanteMesaCinco = qtJogadoresPorMesa - ttlJogadoresMesaCinco;
                    if (ttlJogadoresFaltanteMesaCinco > 0) {
                        jogadorDaEtapa.setMesa(5);
                        jogadorDaEtapa.setPosicaoMesa(ttlJogadoresMesaCinco + 1);
                        daoJogadordaEtapa.edita(jogadorDaEtapa);
                        jogadorEncontrouMesa = true;
                    }
                }
            }

            jogadorRecuperar = jogadorRecuperar + 1;
            if (jogadorRecuperar > ttlJogadoresMesaQuebrar) {
                jogadorRecuperar = 1;
            }
        }
    }

    private void balanceMesasSimples() {
        JogadorDaEtapa jogadorDaEtapa = daoJogadordaEtapa.jogadorEspecificoPorPosicaoEMesa(mesaComMaisJogadores, ttlJogadoresMesaComMaisJogadores);
        jogadorDaEtapa.setMesa(mesaComMenosJogadores);
        jogadorDaEtapa.setPosicaoMesa(ttlJogadoresMesaComMenosJogadores + 1);
        daoJogadordaEtapa.edita(jogadorDaEtapa);
        avisaJogadorBalanceado(jogadorDaEtapa);
    }




    private void avisaJogadorBalanceado(JogadorDaEtapa jogador) {
        Intent vaParaBalanceAviso =
                new Intent(this,
                        MainBalanceAvisoActivity.class);
        vaParaBalanceAviso.putExtra(CHAVE_JOGADOR, jogador);
        startActivity(vaParaBalanceAviso);
    }


    private void avisaJogadorBalanceadoQuebraMesa() {
        Intent vaParaBalanceAviso =
                new Intent(this,
                        MainBalanceAvisoActivity.class);
        startActivity(vaParaBalanceAviso);
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaMesaParaEliminacaoBalanceView.atualizaLista();

    }

    private void configuraListaJogadores() {
        ListView listaJogador = findViewById(R.id.activity_mesa_eliminacao_balance);
        listaMesaParaEliminacaoBalanceView.configuraAdapter(listaJogador);
        registerForContextMenu(listaJogador);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_balance_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemIdMenu = item.getItemId();
        if (itemIdMenu == R.id.activity_balance_menu) {
            balanceMesas();

            if (ttlMesaDeveriaTer == ttlMesas) {
                balanceMesasSimples();
            } else {
                balanceMesasQuebra();
                avisaJogadorBalanceadoQuebraMesa();
            }
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void balanceMesas() {

        for (int i = 0; i < ttlMesas; i++) {
            int posicaoBusca = 0;
            novaPosicao = 1;
            JogadorDaEtapa jogadorDaEtapa = daoJogadordaEtapa.jogadorDealerNaMesa(i + 1);
            posicaoBusca = jogadorDaEtapa.getPosicaoMesa();
            jogadorDaEtapa.setPosicaoMesa(novaPosicao);
            daoJogadordaEtapa.edita(jogadorDaEtapa);


            ttlJogadoresPorMesa[i] = daoJogadordaEtapa.ttlJogadoresPorMesa(i + 1);
            for (int j = 1; j < 9; j++) {
                jogadorDaEtapa = null;
                posicaoBusca = posicaoBusca + 1;

                if (posicaoBusca > 9) {
                    posicaoBusca = 1;
                }
                jogadorDaEtapa = daoJogadordaEtapa.jogadorEspecificoPorPosicaoEMesa(i + 1, posicaoBusca);

                if (jogadorDaEtapa != null) {

                    novaPosicao = novaPosicao + 1;
                    jogadorDaEtapa.setPosicaoMesa(novaPosicao);
                    jogadorDaEtapa.setNovoDealear(true);
                    daoJogadordaEtapa.edita(jogadorDaEtapa);

                    if (ttlJogadoresPorMesa[i] == novaPosicao) {
                        j = 10;
                    }
                }
            }

        }
        daoJogadordaEtapa.retiraCondicaoDealerParaTodosJogadores(false);
//        List<JogadorDaEtapa> jogadorDaEtapas = daoJogadordaEtapa.todosOrdemMesaNaoEliminados();
//
//        for (int i = 0; i < jogadorDaEtapas.size(); i++) {
//            JogadorDaEtapa jogadorDaEtapas1 = jogadorDaEtapas.get(i);
//            jogadorDaEtapas1.setNovoDealear(false);
//            daoJogadordaEtapa.edita(jogadorDaEtapas1);
//        }
    }


    private void carregaResumo() {

        ttlMesas = daoJogadordaEtapa.ttlMesas();
        ttlJogadoresPorMesa = new int[ttlMesas];

        int totalJogadores = daoJogadordaEtapa.ttlJogadoresParaSeremEliminados();

        int resto = totalJogadores % 9;

        if (resto == 0) {
            ttlMesaDeveriaTer = totalJogadores / 9;
        } else {
            ttlMesaDeveriaTer = (totalJogadores / 9) + 1;
        }


        for (int i = 0; i < ttlMesas; i++) {

            ttlJogadoresPorMesa[i] = daoJogadordaEtapa.ttlJogadoresPorMesa(i + 1);


            if (ttlJogadoresPorMesa[i] >= ttlJogadoresMesaComMaisJogadores) {
                ttlJogadoresMesaComMaisJogadores = ttlJogadoresPorMesa[i];
                mesaComMaisJogadores = i + 1;
            }

            if (ttlJogadoresPorMesa[i] <= ttlJogadoresMesaComMenosJogadores) {
                ttlJogadoresMesaComMenosJogadores = ttlJogadoresPorMesa[i];
                mesaComMenosJogadores = i + 1;
            }
        }

        if (ttlJogadoresMesaComMaisJogadores > ttlJogadoresMesaComMenosJogadores + 1) {

        } else {
            if (ttlMesas == ttlMesaDeveriaTer) {
                finish();
            }
        }


    }
}


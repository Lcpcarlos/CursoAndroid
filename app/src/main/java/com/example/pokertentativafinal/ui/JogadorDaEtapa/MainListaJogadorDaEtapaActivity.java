package com.example.pokertentativafinal.ui.JogadorDaEtapa;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pokertentativafinal.DAO.JogadorDAO;
import com.example.pokertentativafinal.DAO.JogadorDaEtapaDAO;
import com.example.pokertentativafinal.R;
import com.example.pokertentativafinal.database.PokerDatabase;
import com.example.pokertentativafinal.database.dao.RoomJogadorDAO;
import com.example.pokertentativafinal.database.dao.RoomJogadorDaEtapaDAO;
import com.example.pokertentativafinal.model.Jogador;
import com.example.pokertentativafinal.model.JogadorDaEtapa;
import com.example.pokertentativafinal.ui.Jogador.ActivityFormularioJogador;
import com.example.pokertentativafinal.ui.JogadorDaEtapa.Adapter.ListaJogadoresDaEtapaAdapter;
import com.example.pokertentativafinal.ui.MesaParaEliminacao.MainMesaEliminacao;
import com.example.pokertentativafinal.ui.Mesas.MainMesa;
import com.example.pokertentativafinal.ui.Rebuy.MainRebuy;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainListaJogadorDaEtapaActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR = "Lista de Jogadores para Etapa";

    private final JogadorDAO daoJogador = new JogadorDAO();
    private ListaJogadoresDaEtapaAdapter adapter;
    private ListaJogdorDaEtapaView listaJogdorDaEtapaView;
    private CarregaListaDeJogadoresParaSelecionar carregaListaDeJogadoresParaSelecionar;
    private RoomJogadorDaEtapaDAO daoJogadordaEtapa;
    private JogadorDaEtapaDAO jogadorDaEtapaDAO;
    private JogadorDaEtapa jogadorSelecionado;
    private boolean temJogadorSelecionado = false;
    private int resto;
    private int ttlMesa;
    private int mesaEscolhida;
    private int posicaoEscolhida;
    private int[] ttlJogadoresPorMesa;
    private boolean continua = true;
    private int ttlJogadoresDefinido;
    private int[][] mesaComPosicao;
    private RoomJogadorDAO jogadorDAO;
    private Jogador jogadorRemover;
    private SorteiaJogadoresPorMesa sorteiaJogadoresPorMesa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lista_jogadordaetapa);

        daoJogadordaEtapa = PokerDatabase.getInstance(this)
                .getRoomJogadorDaEtapaDAO();


        setTitle(TITULO_APPBAR);

        listaJogdorDaEtapaView = new ListaJogdorDaEtapaView(this);
        carregaListaDeJogadoresParaSelecionar =
                new CarregaListaDeJogadoresParaSelecionar(this);
        carregaListaDeJogadoresParaSelecionar.carregaLista();
        configuraFABNovoJogador();
        configuraListaJogadores();
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaListaDeJogadoresParaSelecionar.carregaLista();
        listaJogdorDaEtapaView.atualizaLista();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_jogador_da_etapa_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int itemIdMenu = item.getItemId();
        if (itemIdMenu == R.id.activity_jogador_da_etapa_menu_sorteio) {
            final Intent vaiParaMesa = new Intent(this, MainMesa.class);

            if (daoJogadordaEtapa.todos().size() > 0) {
                temJogadorSelecionado = false;
                verificaTemJogadorSelecionado();
                if (temJogadorSelecionado) {
                    perguntaSeQuerRefazerOSorteio(vaiParaMesa, this);

                } else {
                    avisaQueNaoTemJogadorParaSorteio();
                }

            } else {
                temJogadorSelecionado = false;
                verificaTemJogadorSelecionado();
                if (temJogadorSelecionado) {
                    perguntaSeQuerEfetuarOSorteio(vaiParaMesa, this);
                } else {
                    avisaQueNaoTemJogadorParaSorteio();
                }
            }
        }

        if (itemIdMenu == R.id.activity_jogador_da_etapa_menu_mesa) {
            startActivity(new Intent(this, MainMesa.class));
        }

        if (itemIdMenu == R.id.activity_jogador_da_etapa_menu_rebuy) {
            startActivity(new Intent(this, MainRebuy.class));
        }

        if (itemIdMenu == R.id.activity_jogador_da_etapa_menu_novojogador) {
            incluiNovoJogadorNaEtapa();
        }

        if (itemIdMenu == R.id.activity_jogador_da_etapa_menu_base) {
            PokerDatabase database = PokerDatabase.getInstance(this);
            RoomJogadorDAO jogadorDAO = database.getRoomJogadorDAO();
            if (jogadorDAO.todos().size() > 0) {
                avisaQueBaseJaFoiGerada();
            } else {
                criaJogadoresDeTeste();
                jogadorDaEtapaDAO.limpa();
                carregaListaDeJogadoresParaSelecionar.carregaLista();
                listaJogdorDaEtapaView.atualizaLista();
            }


        }

        if (itemIdMenu == R.id.activity_jogador_da_etapa_menu_balance_mesa) {
            startActivity(new Intent(this, MainMesaEliminacao.class));
        }

        return super.onOptionsItemSelected(item);
    }

    private void avisaQueNaoTemJogadorParaSorteio() {
        new AlertDialog.Builder(this)
                .setTitle("Efetuar Sorteio")
                .setMessage("Náo existem jogadores salvos na etapa.")
                .setNegativeButton("ok", null)
                .show();
    }


    private void perguntaSeQuerRefazerOSorteio(final Intent vaiParaMesa, final Context context) {
        new AlertDialog.Builder(this)
                .setTitle("Efetuar Sorteio")
                .setMessage("Sorteio já efetuado. Refazer o sorteio?")
                .setPositiveButton("sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        salvarJogadorNaEtapa();
                        SorteiaJogadoresPorMesa sorteiaJogadoresPorMesa =
                                new SorteiaJogadoresPorMesa(context);
                        sorteiaJogadoresPorMesa.carregaLista();
                        startActivity(vaiParaMesa);
                    }
                })
                .setNegativeButton("Náo", null)
                .show();
    }

    private void perguntaSeQuerEfetuarOSorteio(final Intent vaiPraMesa, final Context context) {
        new AlertDialog.Builder(this)
                .setTitle("Efetuar Sorteio")
                .setMessage("Tem certeza?")
                .setPositiveButton("sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        salvarJogadorNaEtapa();
                        SorteiaJogadoresPorMesa sorteiaJogadoresPorMesa =
                                new SorteiaJogadoresPorMesa(context);
                        sorteiaJogadoresPorMesa.carregaLista();
                        startActivity(vaiPraMesa);
                    }
                })
                .setNegativeButton("Náo", null)
                .show();
    }

    private void avisaQueBaseJaFoiGerada() {
        new AlertDialog.Builder(this)
                .setTitle("Base já gerada.")
                .setMessage("Base já carregada")
                .setNegativeButton("ok", null)
                .show();
    }

    private void avisaSoPodeIncluirUmDeCadaVez() {
        new AlertDialog.Builder(this)
                .setTitle("Só pode incluir um de cada vez")
                .setMessage("Só pode incluir um de cada vez ")
                .setNegativeButton("ok", null)
                .show();
    }

    private void avisaMesasFechadasNaoPodeIncluir() {
        new AlertDialog.Builder(this)
                .setTitle("Mesas fechadas")
                .setMessage("Mesas fechadas não pode incluir. Tem de fazer novo sorteio")
                .setNegativeButton("ok", null)
                .show();
    }

    private void salvarJogadorNaEtapa() {
        JogadorDaEtapaDAO jogadorDaEtapaDAO = new JogadorDaEtapaDAO();
        JogadorDaEtapa jogadorDaEtapaSelecionado = new JogadorDaEtapa();
        daoJogadordaEtapa.limpaBaseJogadorDaEtapa();

        for (int i = 0; i < jogadorDaEtapaDAO.todos().size(); i++) {
            jogadorDaEtapaSelecionado = jogadorDaEtapaDAO.todos().get(i);
            if (jogadorDaEtapaDAO.todos().get(i).isCheck()) {
                daoJogadordaEtapa.salva(jogadorDaEtapaSelecionado);
            }
        }
    }

    private void verificaTemJogadorSelecionado() {
        JogadorDaEtapaDAO jogadores = new JogadorDaEtapaDAO();
        for (int i = 0; i < jogadores.todos().size(); i++) {
            if (jogadores.todos().get(i).isCheck()) {
                temJogadorSelecionado = true;
            }
        }
    }


    private void configuraListaJogadores() {
        ListView listaJogador = findViewById(R.id.activity_main_lista_de_jogadordaetapa);
        listaJogdorDaEtapaView.configuraAdapter(listaJogador);
    }

    private void configuraFABNovoJogador() {
        FloatingActionButton btnNovoJogador =
                findViewById(R.id.Activity_lista_jogador_FAB_novo_jogador);

        btnNovoJogador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abreFormularioInsereJogador();
            }
        });
    }

    private void abreFormularioInsereJogador() {
        startActivity(new Intent(this, ActivityFormularioJogador.class));
    }

    private void criaJogadoresDeTeste() {
        PokerDatabase database = PokerDatabase.getInstance(this);
        RoomJogadorDAO jogadorDAO = database.getRoomJogadorDAO();
        jogadorDAO.limpaBaseJogador();

        jogadorDAO.salva(new Jogador("Marisa", "3321"));
        jogadorDAO.salva(new Jogador("De Deus", "1321"));
        jogadorDAO.salva(new Jogador("Taís", "1321"));
        jogadorDAO.salva(new Jogador("Marcos Taís", "1321"));
        incluiResponsavelFinanceiro("De Deus", "De Deus");
        incluiResponsavelFinanceiro("Marisa", "De Deus");
        incluiResponsavelFinanceiro("Taís", "De Deus");
        incluiResponsavelFinanceiro("Marcos Taís", "De Deus");

        jogadorDAO.salva(new Jogador("Roberto", "2321"));

        jogadorDAO.salva(new Jogador("Thiago", "4321"));
        incluiResponsavelFinanceiro("Thiago", "Thiago");
        jogadorDAO.salva(new Jogador("Simone", "5321"));
        jogadorDAO.salva(new Jogador("Maria Thiago", "12321"));
        incluiResponsavelFinanceiro("Simone", "Thiago");
        incluiResponsavelFinanceiro("Maria Thiago", "Thiago");

        jogadorDAO.salva(new Jogador("João Almeida", "4321"));
        incluiResponsavelFinanceiro("João Almeida", "João Almeida");
        jogadorDAO.salva(new Jogador("Maristela", "5321"));
        incluiResponsavelFinanceiro("Maristela", "João Almeida");


        jogadorDAO.salva(new Jogador("Ricardo(Ulysses)", "12321"));
        jogadorDAO.salva(new Jogador("Renata", "6321"));
        jogadorDAO.salva(new Jogador("Ulysses", "7321"));
        incluiResponsavelFinanceiro("Renata", "Ulysses");
        incluiResponsavelFinanceiro("Ulysses", "Ulysses");
        incluiResponsavelFinanceiro("Ricardo(Ulysses)", "Ulysses");

        jogadorDAO.salva(new Jogador("Pablo", "8321"));

        jogadorDAO.salva(new Jogador("Patricia BB", "9321"));

        jogadorDAO.salva(new Jogador("Osni", "01321"));
        jogadorDAO.salva(new Jogador("Gustavo Osni", "01321"));
        jogadorDAO.salva(new Jogador("Chris Gustavo", "01321"));
        incluiResponsavelFinanceiro("Osni", "Osni");
        incluiResponsavelFinanceiro("Gustavo Osni", "Osni");
        incluiResponsavelFinanceiro("Chris Gustavo", "Osni");

        jogadorDAO.salva(new Jogador("Rossato", "11321"));
        jogadorDAO.salva(new Jogador("Lenara", "1232"));
        incluiResponsavelFinanceiro("Rossato", "Rossato");
        incluiResponsavelFinanceiro("Lenara", "Rossato");


        jogadorDAO.salva(new Jogador("Aécio", "12321"));
        jogadorDAO.salva(new Jogador("Cristina", "12321"));
        jogadorDAO.salva(new Jogador("Maria Luiza", "12321"));
        incluiResponsavelFinanceiro("Aécio", "Aécio");
        incluiResponsavelFinanceiro("Cristina", "Aécio");
        incluiResponsavelFinanceiro("Maria Luiza", "Aécio");


        jogadorDAO.salva(new Jogador("Buzz", "12321"));

        jogadorDAO.salva(new Jogador("Mano", "12321"));
        jogadorDAO.salva(new Jogador("Arthur Mano", "12321"));
        incluiResponsavelFinanceiro("Mano", "Mano");
        incluiResponsavelFinanceiro("Arthur Mano", "Mano");

        jogadorDAO.salva(new Jogador("Olier", "12321"));

        jogadorDAO.salva(new Jogador("Isabela", "12321"));

        jogadorDAO.salva(new Jogador("Masilva", "12321"));

        jogadorDAO.salva(new Jogador("Mateus Rodrigues", "12321"));

        jogadorDAO.salva(new Jogador("Eudes Medico", "12321"));
        jogadorDAO.salva(new Jogador("Patricia de Castro", "12321"));

        jogadorDAO.salva(new Jogador("Ferdinando", "12321"));


        jogadorDAO.salva(new Jogador("EudesBB", "12321"));
        incluiResponsavelFinanceiro("EudesBB", "EudesBB");
        jogadorDAO.salva(new Jogador("Artur-EudesBB", "12321"));
        incluiResponsavelFinanceiro("Artur-EudesBB", "EudesBB");
        jogadorDAO.salva(new Jogador("Natália-EudesBB", "12321"));
        incluiResponsavelFinanceiro("Natália-EudesBB", "EudesBB");

        jogadorDAO.salva(new Jogador("Derick Marcelino", "12321"));

        jogadorDAO.salva(new Jogador("Andressa", "12321"));
        jogadorDAO.salva(new Jogador("Vinícius", "12321"));
        incluiResponsavelFinanceiro("Vinícius", "Vinícius");
        incluiResponsavelFinanceiro("Andressa", "Vinícius");

        jogadorDAO.salva(new Jogador("Cascata", "12321"));
        jogadorDAO.salva(new Jogador("Luzia", "12321"));
        incluiResponsavelFinanceiro("Cascata", "Cascata");
        incluiResponsavelFinanceiro("Luzia", "Cascata");


        jogadorDAO.salva(new Jogador("Otoval", "12321"));

        jogadorDAO.salva(new Jogador("Mozaniel", "12321"));
        jogadorDAO.salva(new Jogador("Raquel Mozaniel", "12321"));
        jogadorDAO.salva(new Jogador("Pedro Mozaniel", "12321"));
        jogadorDAO.salva(new Jogador("Letícia Mozaniel", "12321"));
        incluiResponsavelFinanceiro("Mozaniel", "Mozaniel");
        incluiResponsavelFinanceiro("Raquel Mozaniel", "Mozaniel");
        incluiResponsavelFinanceiro("Pedro Mozaniel", "Mozaniel");
        incluiResponsavelFinanceiro("Letícia Mozaniel", "Mozaniel");

        jogadorDAO.salva(new Jogador("Heglison", "12321"));

        jogadorDAO.salva(new Jogador("Badu", "12321"));

        jogadorDAO.salva(new Jogador("Luiz", "123"));
        jogadorDAO.salva(new Jogador("Dayane", "12321"));
        incluiResponsavelFinanceiro("Luiz", "Luiz");
        incluiResponsavelFinanceiro("Dayane", "Luiz");


        jogadorDAO.salva(new Jogador("Jairo", "12321"));

        jogadorDAO.salva(new Jogador("Rafael", "12321"));

        jogadorDAO.salva(new Jogador("Wanderley", "12321"));
        jogadorDAO.salva(new Jogador("Athina", "12321"));
        incluiResponsavelFinanceiro("Wanderley", "Wanderley");
        incluiResponsavelFinanceiro("Athina", "Wanderley");

        jogadorDAO.salva(new Jogador("Adriano Baea", "12321"));

        jogadorDAO.salva(new Jogador("Henderson", "12321"));

        jogadorDAO.salva(new Jogador("Igor", "12321"));
        jogadorDAO.salva(new Jogador("Joáo Pedro", "12321"));
        jogadorDAO.salva(new Jogador("Fabrícia Igor", "12321"));
        jogadorDAO.salva(new Jogador("Francisco Igor", "12321"));
        incluiResponsavelFinanceiro("Igor", "Igor");
        incluiResponsavelFinanceiro("Joáo Pedro", "Igor");
        incluiResponsavelFinanceiro("Fabrícia Igor", "Igor");
        incluiResponsavelFinanceiro("Francisco Igor", "Igor");

        jogadorDAO.salva(new Jogador("Hauseman", "12321"));
        jogadorDAO.salva(new Jogador("Hauseman Felipe", "12321"));

        jogadorDAO.salva(new Jogador("Mamá", "12321"));
        jogadorDAO.salva(new Jogador("Victor", "12321"));
        incluiResponsavelFinanceiro("Mamá", "Mamá");
        incluiResponsavelFinanceiro("Victor", "Mamá");

        jogadorDAO.salva(new Jogador("Fred", "12321"));
        jogadorDAO.salva(new Jogador("Ynara", "12321"));
        incluiResponsavelFinanceiro("Fred", "Fred");
        incluiResponsavelFinanceiro("Ynara", "Fred");

        jogadorDAO.salva(new Jogador("Jackson", "12321"));

        jogadorDAO.salva(new Jogador("Felipe", "12321"));
        jogadorDAO.salva(new Jogador("Karol", "12321"));
        incluiResponsavelFinanceiro("Felipe", "Felipe");
        incluiResponsavelFinanceiro("Karol", "Felipe");

        jogadorDAO.salva(new Jogador("Adriano Rosa", "12321"));

        jogadorDAO.salva(new Jogador("Amaro", "12321"));
        jogadorDAO.salva(new Jogador("Leticia Amaro", "12321"));
        incluiResponsavelFinanceiro("Amaro", "Amaro");
        incluiResponsavelFinanceiro("Leticia Amaro", "Amaro");
    }

    private void incluiResponsavelFinanceiro(String nomeJogador, String nomeJogadorResponsavel) {
        PokerDatabase database = PokerDatabase.getInstance(this);
        RoomJogadorDAO jogadorDAO = database.getRoomJogadorDAO();
        Jogador jogador;
        jogador = jogadorDAO.jogadorPorNome(nomeJogador);
        Jogador jogadorResponsavel = jogadorDAO.jogadorPorNome(nomeJogadorResponsavel);
        jogador.setIdResponsavelFinanceiroa(jogadorResponsavel.getId());
        jogadorDAO.edita(jogador);

    }

    private void incluiNovoJogadorNaEtapa() {
        List<JogadorDaEtapa> todosJogadores = daoJogadordaEtapa.todos();
        int ttlJogadoresParaEtapa = daoJogadordaEtapa.ttlJogadoresParaEtapa();
        int ttlJogadoresParaEtapaJaNasMesas = daoJogadordaEtapa.ttlJogadoresParaEtapaJaNasMesas();
        JogadorDaEtapaDAO jogadorDaEtapaDAO = new JogadorDaEtapaDAO();
        jogadorDaEtapaDAO.contaJogadoresMarcadosNaEtapa();
        if (todosJogadores.size() % 9 == 0) {
            avisaMesasFechadasNaoPodeIncluir();
        } else {
            if (ttlJogadoresParaEtapaJaNasMesas + 1  == jogadorDaEtapaDAO.contaJogadoresMarcadosNaEtapa()) {
                encontraTotalDeMesas(todosJogadores);
                int mesa = 0;
                ttlJogadoresPorMesa = new int[ttlMesa];

                for (int i = 0; i < ttlMesa; i++) {
                    mesa = i + 1;
                    ttlJogadoresPorMesa[i] = daoJogadordaEtapa.mesas(mesa);
                }

                colocaJogadoresNaMesa();
            } else    {
                avisaSoPodeIncluirUmDeCadaVez();
            }
        }
    }

    private void encontraTotalDeMesas(List<JogadorDaEtapa> todosJogadores) {
        resto = todosJogadores.size() % 9;

        if (resto == 0) {
            ttlMesa = todosJogadores.size() / 9;
        } else {
            ttlMesa = (todosJogadores.size() / 9) + 1;
        }
    }


    private void colocaJogadoresNaMesa() {
        JogadorDaEtapaDAO jogadorDaEtapaDAO = new JogadorDaEtapaDAO();
        JogadorDaEtapa jogadorDaEtapaSelecionado = new JogadorDaEtapa();
        for (int i = 0; i < jogadorDaEtapaDAO.todos().size(); i++) {
            if (jogadorDaEtapaDAO.todos().get(i).isCheck()) {
                jogadorDaEtapaSelecionado = jogadorDaEtapaDAO.todos().get(i);
                int idJogador = jogadorDaEtapaSelecionado.getId();
                if (daoJogadordaEtapa.jogadorEspecifico(idJogador) == null) {
                    mesaEscolhida = 9;
                    posicaoEscolhida = 9;
                    defineMesaPosicao();
                    mesaEscolhida = mesaEscolhida + 1;
                    posicaoEscolhida = posicaoEscolhida + 1;
                    jogadorDaEtapaSelecionado.setMesa(mesaEscolhida);
                    jogadorDaEtapaSelecionado.setPosicaoMesa(posicaoEscolhida);
                    daoJogadordaEtapa.salva(jogadorDaEtapaSelecionado);
                }
            }
        }
    }

    private void defineMesaPosicao() {

        for (int i = 0; i < ttlMesa; i++) {
            if (ttlJogadoresPorMesa[i] < posicaoEscolhida) {
                mesaEscolhida = i;
                posicaoEscolhida = ttlJogadoresPorMesa[i];
            }
        }
    }
}

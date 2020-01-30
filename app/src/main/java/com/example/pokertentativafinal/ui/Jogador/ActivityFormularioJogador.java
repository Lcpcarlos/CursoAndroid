package com.example.pokertentativafinal.ui.Jogador;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pokertentativafinal.R;
import com.example.pokertentativafinal.database.PokerDatabase;
import com.example.pokertentativafinal.database.dao.RoomJogadorDAO;
import com.example.pokertentativafinal.model.Jogador;

import static com.example.pokertentativafinal.ui.ConstantesActivities.CHAVE_JOGADOR;

public class ActivityFormularioJogador extends AppCompatActivity {

    private static final String TITULO_APPBAR_NOVO_JOGADOR = "Novo Jogador";
    private static final String TITULO_APPBAR_EDITA_JOGADOR = "Editar Jogador";
    private EditText nomeJogador;
    private EditText telefoneCelularJogador;
    private  RoomJogadorDAO daoJogador;
    private Jogador jogadorRecebido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_jogador);

               daoJogador = PokerDatabase.getInstance(this)
                       .getRoomJogadorDAO();

        inicializaCampoNovoJogador();


        carregaJogador();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_jogador_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemIdMenu = item.getItemId();
        if (itemIdMenu == R.id.activity_formulario_jogador_menu) {
            finalizaFormulario();
        }
        return super.onOptionsItemSelected(item);
    }

    private void carregaJogador() {
        Intent dadoJogador = getIntent();
        if (dadoJogador.hasExtra(CHAVE_JOGADOR)) {
            jogadorRecebido = (Jogador) dadoJogador.getSerializableExtra(CHAVE_JOGADOR);
            preencheJogadores();
            setTitle(TITULO_APPBAR_EDITA_JOGADOR);
        } else {
            setTitle(TITULO_APPBAR_NOVO_JOGADOR);
            jogadorRecebido = new Jogador();
        }
    }

    private void preencheJogadores() {
        nomeJogador.setText(jogadorRecebido.getNome());
        telefoneCelularJogador.setText(jogadorRecebido.getTelefone());
    }

    private void finalizaFormulario() {
        preencheJogador();
        if (jogadorRecebido.temIdValido()) {
            daoJogador.edita(jogadorRecebido);
        } else {
            daoJogador.salva(jogadorRecebido);
        }
        finish();
    }

    private void inicializaCampoNovoJogador() {
        nomeJogador = findViewById(R.id.activity_formulario_jogador_nome);
        telefoneCelularJogador =
                findViewById(R.id.activity_formulario_jogador_telefone_celular);
    }

    private void preencheJogador() {
        String nome = nomeJogador.getText().toString();
        String telefone = telefoneCelularJogador.getText().toString();
        jogadorRecebido.setNome(nome);
        jogadorRecebido.setTelefone(telefone);

    }
}

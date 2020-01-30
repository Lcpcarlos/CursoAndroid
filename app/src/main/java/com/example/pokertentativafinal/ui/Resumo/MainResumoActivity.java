package com.example.pokertentativafinal.ui.Resumo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

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
        private EditText total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_resumo);

            setTitle(TITTLE_APPBAR);
            daoJogadordaEtapa = PokerDatabase.getInstance(this)
                    .getRoomJogadorDaEtapaDAO();

            inicializaCampoNovoJogador();
        }

        @Override
        protected void onResume() {
            super.onResume();
            inicializaCampoNovoJogador();
        }
        private void inicializaCampoNovoJogador() {
            List<JogadorDaEtapa> jogadores = daoJogadordaEtapa.todos();
            double totalGeral = 0;
            for (int i = 0; i < jogadores.size() ; i++) {
                totalGeral = totalGeral + jogadores.get(i).getTtlPagar();
            }
            total = findViewById(R.id.activity_resumo_da_etapa_total);
            total.setText(String.valueOf(totalGeral));
        }
}

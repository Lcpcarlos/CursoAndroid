package com.example.pokertentativafinal.ui.Resultado;

import android.content.Context;
import android.util.Log;

import com.example.pokertentativafinal.database.PokerDatabase;
import com.example.pokertentativafinal.database.dao.RoomJogadorDaEtapaDAO;
import com.example.pokertentativafinal.model.JogadorDaEtapa;

import java.util.List;

public class CarregaListaDeResultado {
    private final Context context;
    private final RoomJogadorDaEtapaDAO jogadorDaEtapaDAO;
    private Object NullPointerException;
    private int resto;
    private int ttlMesa;
    private int mesaEscolhida;
    private int posicaoEscolhida;
    private  JogadorDaEtapa jogadorDaEtapa;
    private int[] ttlJogadoresPorMesa;
    private boolean continua = true;
    private int ttlJogadoresDefinido;
    private  int[][] mesaComPosicao;


    public CarregaListaDeResultado(Context context) {
        this.context = context;
        this.jogadorDaEtapaDAO = PokerDatabase
                .getInstance(context)
                .getRoomJogadorDaEtapaDAO();
    }

    public void carregaLista() {
        List<JogadorDaEtapa> todosJogadores = jogadorDaEtapaDAO.todos();
        for (int i = 0; i < todosJogadores.size(); i++) {
            double valorAPagarDeRebuy = 0 ;
            double valorTotalAPagar = 0;
            double valorRaike = 0;
            double valorBuyIn = 10;
            double valorAddOn = 0;
            if (todosJogadores.get(i).isRaike()){
                valorRaike= 10;
            }
            if (todosJogadores.get(i).isAddOn()){
                valorAddOn= 10;
            }

            int qtRebuY = todosJogadores.get(i).getQtReBuy();
            int qtRebuyDez = 0;
            int qtRebuyCinco = 2;
            if (qtRebuY > qtRebuyCinco) {
                qtRebuyDez = qtRebuY - 2;
                valorAPagarDeRebuy = ( qtRebuyCinco * 10) + ( qtRebuyDez * 10 );
            } else {
                valorAPagarDeRebuy = qtRebuY * 10;
            }
            valorTotalAPagar = valorBuyIn +
                    (valorRaike) +
                    (valorAPagarDeRebuy) +
                    (valorAddOn) ;
            todosJogadores.get(i).setTtlPagar(valorTotalAPagar);
            jogadorDaEtapaDAO.edita(todosJogadores.get(i));
        }
    }

    public void somaValorAPagar() {
        List<JogadorDaEtapa> todosJogadores = jogadorDaEtapaDAO.todos();
        for (int i = 0; i < todosJogadores.size(); i++) {
            double valorTotalAPagar = jogadorDaEtapaDAO.totalPagar(todosJogadores.get(i).getId());
            todosJogadores.get(i).setTtlPagarFamilia(valorTotalAPagar);
            jogadorDaEtapaDAO.edita(todosJogadores.get(i));
        }
    }
}


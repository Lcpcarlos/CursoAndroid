package com.example.pokertentativafinal;

import android.app.Application;

import com.example.pokertentativafinal.DAO.JogadorDAO;
import com.example.pokertentativafinal.model.Jogador;

@SuppressWarnings("WeakerAccess")
public class PokerAplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        criaJogadoresDeTeste();


    }

    private void criaJogadoresDeTeste() {
        JogadorDAO jogadorDAO = new JogadorDAO();

        jogadorDAO.salva(new Jogador("Luiz", "123"));
        jogadorDAO.salva(new Jogador("Carlos1", "1321"));
        jogadorDAO.salva(new Jogador("Carlos2", "2321"));
        jogadorDAO.salva(new Jogador("Carlos3", "3321"));
        jogadorDAO.salva(new Jogador("Carlos4", "4321"));
        jogadorDAO.salva(new Jogador("Carlos5", "5321"));
        jogadorDAO.salva(new Jogador("Carlos6", "6321"));
        jogadorDAO.salva(new Jogador("Carlos7", "7321"));
        jogadorDAO.salva(new Jogador("Carlos8", "8321"));
        jogadorDAO.salva(new Jogador("Carlos9", "9321"));
        jogadorDAO.salva(new Jogador("Carlos10", "01321"));
        jogadorDAO.salva(new Jogador("Carlos11", "11321"));
        jogadorDAO.salva(new Jogador("Carlos12", "12321"));
    }
}

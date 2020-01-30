package com.example.pokertentativafinal;

import android.app.Application;

import androidx.room.Room;

import com.example.pokertentativafinal.DAO.JogadorDAO;
import com.example.pokertentativafinal.database.PokerDatabase;
import com.example.pokertentativafinal.database.dao.RoomJogadorDAO;
import com.example.pokertentativafinal.model.Jogador;

@SuppressWarnings("WeakerAccess")
public class PokerAplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        criaJogadoresDeTeste();


    }

    private void criaJogadoresDeTeste() {
        PokerDatabase database = PokerDatabase.getInstance(this);
        RoomJogadorDAO jogadorDAO = database.getRoomJogadorDAO();

        jogadorDAO.salva(new Jogador("Luiz", "123"));
        jogadorDAO.salva(new Jogador("De Deus", "1321"));
        jogadorDAO.salva(new Jogador("Roberto", "2321"));
        jogadorDAO.salva(new Jogador("Marisa", "3321"));
        jogadorDAO.salva(new Jogador("Thiago", "4321"));
        jogadorDAO.salva(new Jogador("Japa", "5321"));
        jogadorDAO.salva(new Jogador("Renata", "6321"));
        jogadorDAO.salva(new Jogador("Ulysses", "7321"));
        jogadorDAO.salva(new Jogador("Pablo", "8321"));
        jogadorDAO.salva(new Jogador("Patricia BB", "9321"));
        jogadorDAO.salva(new Jogador("Osni", "01321"));
        jogadorDAO.salva(new Jogador("Rossato", "11321"));
        jogadorDAO.salva(new Jogador("Lenara", "12321"));
        jogadorDAO.salva(new Jogador("Aécio", "12321"));
        jogadorDAO.salva(new Jogador("Cristina", "12321"));
        jogadorDAO.salva(new Jogador("Buzz", "12321"));
        jogadorDAO.salva(new Jogador("Mano", "12321"));
        jogadorDAO.salva(new Jogador("Arthur", "12321"));
        jogadorDAO.salva(new Jogador("Olier", "12321"));
        jogadorDAO.salva(new Jogador("Izabela", "12321"));
        jogadorDAO.salva(new Jogador("Eudes Martins", "12321"));
        jogadorDAO.salva(new Jogador("Patricia Martins", "12321"));
        jogadorDAO.salva(new Jogador("EudesBB", "12321"));
        jogadorDAO.salva(new Jogador("Andressa", "12321"));
        jogadorDAO.salva(new Jogador("Vinícius", "12321"));
        jogadorDAO.salva(new Jogador("Otoval", "12321"));
        jogadorDAO.salva(new Jogador("Joáo Almeida", "12321"));
        jogadorDAO.salva(new Jogador("Maristela", "12321"));
        jogadorDAO.salva(new Jogador("Mozaniel", "12321"));
        jogadorDAO.salva(new Jogador("Raguel Mozaniel", "12321"));
        jogadorDAO.salva(new Jogador("Heglison", "12321"));
        jogadorDAO.salva(new Jogador("Badu", "12321"));
        jogadorDAO.salva(new Jogador("Dayane", "12321"));
        jogadorDAO.salva(new Jogador("Jairo", "12321"));
        jogadorDAO.salva(new Jogador("Rafael", "12321"));
        jogadorDAO.salva(new Jogador("Maria", "12321"));
        jogadorDAO.salva(new Jogador("Wanderley", "12321"));
        jogadorDAO.salva(new Jogador("Adriano Baea", "12321"));
        jogadorDAO.salva(new Jogador("Henderson", "12321"));
        jogadorDAO.salva(new Jogador("Igor", "12321"));
        jogadorDAO.salva(new Jogador("Joáo Pedro", "12321"));
        jogadorDAO.salva(new Jogador("Hauseman", "12321"));
    }
}

package com.example.pokertentativafinal.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.pokertentativafinal.database.dao.RoomJogadorDAO;
import com.example.pokertentativafinal.database.dao.RoomJogadorDaEtapaDAO;
import com.example.pokertentativafinal.model.Jogador;
import com.example.pokertentativafinal.model.JogadorDaEtapa;

@Database(entities = {Jogador.class, JogadorDaEtapa.class}, version = 5, exportSchema = false)
public abstract class PokerDatabase extends RoomDatabase {

    private static final String NOME_BANCO_DE_DADOS = "poker.db";

    public abstract RoomJogadorDAO getRoomJogadorDAO();

    public abstract RoomJogadorDaEtapaDAO getRoomJogadorDaEtapaDAO();

    public static  PokerDatabase getInstance(Context context) {
        return  Room.
                databaseBuilder(context , PokerDatabase.class, NOME_BANCO_DE_DADOS)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

    }
}

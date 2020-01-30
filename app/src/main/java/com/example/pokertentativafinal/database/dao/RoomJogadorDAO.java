package com.example.pokertentativafinal.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.pokertentativafinal.model.Jogador;

import java.util.List;

@Dao
public interface RoomJogadorDAO {
    @Insert
    void salva(Jogador jogador);

    @Delete
    void remove(Jogador jogador);

    @Query("SELECT * FROM jogador")
    List<Jogador> todos();

    @Update
    void edita(Jogador jogadorRecebido);
}

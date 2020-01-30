package com.example.pokertentativafinal.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.pokertentativafinal.model.JogadorDaEtapa;

import java.util.List;

@Dao
public interface RoomJogadorDaEtapaDAO {
    @Insert
    void salva(JogadorDaEtapa jogador);

    @Delete
    void remove(JogadorDaEtapa jogador);

    @Query("SELECT * FROM JogadorDaEtapa")
    List<JogadorDaEtapa> todos();

    @Query("SELECT * FROM JogadorDaEtapa order by mesa, posicaoMesa")
    List<JogadorDaEtapa> todosOrdemMesa();

    @Update
    void edita(JogadorDaEtapa jogadorRecebido);
}

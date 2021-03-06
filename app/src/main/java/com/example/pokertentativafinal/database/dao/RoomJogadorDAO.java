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

    @Query("SELECT * FROM jogador order by nome")
    List<Jogador> todos();

    @Query("SELECT * FROM jogador where nome = :nome")
    Jogador jogadorPorNome(String nome);

    @Query("SELECT * FROM jogador where idResponsavelFinanceiroa = :idResponsavelFinanceiro")
    Jogador jogadorPorIdFinanceiro(int idResponsavelFinanceiro);

    @Query("SELECT * FROM jogador where id = :id")
    Jogador jogadorPorId(int id);

    @Query("DELETE  FROM jogador")
    void limpaBaseJogador();

    @Update
    void edita(Jogador jogadorRecebido);
}

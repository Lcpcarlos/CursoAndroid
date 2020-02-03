package com.example.pokertentativafinal.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.pokertentativafinal.model.JogadorDaEtapa;

import java.nio.file.attribute.AclEntryPermission;
import java.util.List;

import static java.nio.file.attribute.AclEntryPermission.DELETE;

@Dao
public interface RoomJogadorDaEtapaDAO {
    @Insert
    void salva(JogadorDaEtapa jogador);

    @Delete
    void remove(JogadorDaEtapa jogador);


    @Query("SELECT max(posicaoMesa) FROM JogadorDaEtapa where mesa = :mesa")
    int  mesas(int mesa);

    @Query("SELECT * FROM JogadorDaEtapa")
    List<JogadorDaEtapa> todos();


    @Query("SELECT * FROM JogadorDaEtapa where id = :idJogador")
    JogadorDaEtapa jogadorEspecifico(int idJogador);

    @Query("SELECT * FROM JogadorDaEtapa order by mesa, posicaoMesa")
    List<JogadorDaEtapa> todosOrdemMesa();

    @Update
    void edita(JogadorDaEtapa jogadorRecebido);

    @Query("SELECT * FROM JogadorDaEtapa order by nome")
    List<JogadorDaEtapa> todosOrdemNome();

    @Query("DELETE  FROM JogadorDaEtapa")
    void limpaBaseJogadorDaEtapa();
}

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


    @Query("SELECT max(posicaoMesa) FROM JogadorDaEtapa where mesa = :mesa")
    int  mesas(int mesa);

    @Query("SELECT sum(a.ttlPagar) FROM JogadorDaEtapa a, Jogador b  where b.idResponsavelFinanceiroa = :id and " +
            "                                                              b.id = a.id ")
    double  totalPagar(int id);

    @Query("SELECT * FROM JogadorDaEtapa")
    List<JogadorDaEtapa> todos();


    @Query("SELECT * FROM JogadorDaEtapa where id = :idJogador")
    JogadorDaEtapa jogadorEspecifico(int idJogador);

    @Query("SELECT * FROM JogadorDaEtapa order by mesa, posicaoMesa")
    List<JogadorDaEtapa> todosOrdemMesa();

    @Update
    void edita(JogadorDaEtapa jogadorRecebido);

    @Query("UPDATE  JogadorDaEtapa set  novoDealear = :situacaoDealer " )
    void retiraCondicaoDealerParaTodosJogadores(Boolean situacaoDealer);

    @Query("SELECT * FROM JogadorDaEtapa order by nome")
    List<JogadorDaEtapa> todosOrdemNome();

    @Query("DELETE  FROM JogadorDaEtapa")
    void limpaBaseJogadorDaEtapa();

    @Query("SELECT * FROM JogadorDaEtapa WHERE posicaoDeEliminacao = 0 order by mesa, posicaoMesa")
    List<JogadorDaEtapa> todosOrdemMesaNaoEliminados();

    @Query("SELECT * FROM JogadorDaEtapa WHERE posicaoDeEliminacao = 0 and mesa = :mesa order by mesa, novoDealear desc, posicaoMesa")
    List<JogadorDaEtapa> naoEliminadosOrdemMesaDealerPosicaoPorMesa(int mesa);

    @Query("SELECT COUNT(*) FROM JogadorDaEtapa WHERE posicaoDeEliminacao = 0")
    int ttlJogadoresParaSeremEliminados();

    @Query("SELECT MAX(mesa) FROM JogadorDaEtapa WHERE posicaoDeEliminacao = 0")
    int ttlMesas();

    @Query("SELECT count(*) FROM JogadorDaEtapa WHERE mesa = :mesa and posicaoDeEliminacao = 0")
    int ttlJogadoresPorMesa(int mesa);

    @Query("SELECT * FROM JogadorDaEtapa WHERE mesa = :mesa and posicaoMesa = :posicao and posicaoDeEliminacao = 0 and not novoDealear" )
    JogadorDaEtapa jogadorEspecificoPorPosicaoEMesa(int mesa, int posicao);

    @Query("SELECT MIN(posicaoMesa) FROM JogadorDaEtapa WHERE mesa = :mesa and posicaoMesa >= :posicao and posicaoDeEliminacao = 0" )
    int jogadorPosicao(int mesa, int posicao);

    @Query("SELECT * FROM JogadorDaEtapa WHERE  mesa = :mesa and novoDealear and posicaoDeEliminacao = 0")
    JogadorDaEtapa jogadorDealerNaMesa(int mesa);
}

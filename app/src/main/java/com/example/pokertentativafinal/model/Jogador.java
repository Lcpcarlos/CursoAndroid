package com.example.pokertentativafinal.model;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Jogador implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id = 0;
    private String nome;
    private String telefone;
    private int idResponsavelFinanceiroa = id;

    @Ignore
    public Jogador(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }

    public Jogador() {

    }

    public int getIdResponsavelFinanceiroa() {
        return idResponsavelFinanceiroa;
    }

    public void setIdResponsavelFinanceiroa(int idResponsavelFinanceiroa) {
        this.idResponsavelFinanceiroa = idResponsavelFinanceiroa;
    }

    public  boolean temIdValido() {
        return id > 0;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @NonNull
    @Override
    public String toString() {
        return nome;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

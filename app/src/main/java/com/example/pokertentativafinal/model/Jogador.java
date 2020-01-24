package com.example.pokertentativafinal.model;


import androidx.annotation.NonNull;

import java.io.Serializable;

public class Jogador implements Serializable {

    private int id = 0;
    private String nome;
    private String telefone;

    public Jogador(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }

    public Jogador() {

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

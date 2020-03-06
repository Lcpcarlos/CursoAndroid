package com.example.pokertentativafinal.model;


import java.util.List;

public class JogadorDaEtapaPorMesa {

    int mesa;
    List<String> nomesJogadores;

    public JogadorDaEtapaPorMesa(int mesa, List<String> nomesJogadores) {
        this.mesa = mesa;
        this.nomesJogadores = nomesJogadores;
    }

    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }

    public List<String> getNomesJogadores() {
        return nomesJogadores;
    }

    public void setNomesJogadores(List<String> nomesJogadores) {
        this.nomesJogadores = nomesJogadores;
    }

}

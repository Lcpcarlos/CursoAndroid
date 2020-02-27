package com.example.pokertentativafinal.model;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class JogadorDaEtapa  {

    @PrimaryKey
    private int id = 0;
    private String nome;
    private boolean check = false;
    private int mesa = 0;
    private int posicaoMesa = 0;
    private int qtBuyIn = 0;
    private boolean addOn = true;
    private boolean raike = true;
    private int qtReBuy = 0;
    private double ttlPagar = 0;
    private boolean pagou = false;
    private double ttlPagarFamilia = 0;


    public double getTtlPagarFamilia() {
        return ttlPagarFamilia;
    }

    public void setTtlPagarFamilia(double ttlPagarFamilia) {
        this.ttlPagarFamilia = ttlPagarFamilia;
    }

    public boolean isAddOn() {
        return addOn;
    }

    public void setAddOn(boolean addOn) {
        this.addOn = addOn;
    }

    public boolean isRaike() {
        return raike;
    }

    public void setRaike(boolean raike) {
        this.raike = raike;
    }

    public boolean isPagou() {
        return pagou;
    }

    public void setPagou(boolean pagou) {
        this.pagou = pagou;
    }

    public double getTtlPagar() {
        return ttlPagar;
    }

    public void setTtlPagar(double ttlPagar) {
        this.ttlPagar = ttlPagar;
    }

    public int getPosicaoMesa() {
        return posicaoMesa;
    }

    public void setPosicaoMesa(int posicaoMesa) {
        this.posicaoMesa = posicaoMesa;
    }

    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }

    public int getQtBuyIn() {
        return qtBuyIn;
    }

    public void setQtBuyIn(int qtBuyIn) {
        this.qtBuyIn = qtBuyIn;
    }

    public int getQtReBuy() {
        return qtReBuy;
    }

    public void setQtReBuy(int qtReBuy) {
        this.qtReBuy = qtReBuy;
    }

    @Ignore
    public JogadorDaEtapa(int id, String nome, boolean check) {
        this.id = id;
        this.nome = nome;
        this.check = check;
    }

    public JogadorDaEtapa() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    @NonNull
    @Override
    public String toString() {
        return nome;
    }


}

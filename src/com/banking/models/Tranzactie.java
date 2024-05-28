package com.banking.models;

import com.banking.enums.ModTranzactie;
import com.banking.enums.TipTranzactie;

import java.util.Date;

public class Tranzactie {

    private TipTranzactie tipTranzactie;
    private ModTranzactie modTranzactie;
    private double suma;
    private Date data;

    public Tranzactie(TipTranzactie tipTranzactie, ModTranzactie modTranzactie, double suma, Date data) {
        this.tipTranzactie = tipTranzactie;
        this.modTranzactie = modTranzactie;
        this.suma = suma;
        this.data = data;
    }

    public TipTranzactie getTipTranzactie() {
        return tipTranzactie;
    }

    public void setTipTranzactie(TipTranzactie tipTranzactie) {
        this.tipTranzactie = tipTranzactie;
    }

    public double getSuma() {
        return suma;
    }

    public void setSuma(double suma) {
        this.suma = suma;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public ModTranzactie getModTranzactie() {
        return modTranzactie;
    }

    public void setModTranzactie(ModTranzactie modTranzactie) {
        this.modTranzactie = modTranzactie;
    }

    @Override
    public String toString() {
        return "Tranzactie{" +
                "tipTranzactie=" + tipTranzactie +
                ", modTranzactie=" + modTranzactie +
                ", suma=" + suma +
                ", data=" + data +
                '}';
    }
}

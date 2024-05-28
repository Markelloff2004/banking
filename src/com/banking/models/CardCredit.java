package com.banking.models;

import com.banking.enums.ModTranzactie;
import com.banking.enums.TipTranzactie;

import java.util.Date;

public class CardCredit extends CardBancar{

    private double limitaCredit;

    public CardCredit(String numarCard, String numeProprietar, Date dataExpirarii, double sold, double limitaCredit) {
        super(numarCard, numeProprietar, sold, dataExpirarii);
        this.limitaCredit = limitaCredit;
    }

    @Override
    public void extrage(double suma, ModTranzactie modTranzactie) {
        if(modTranzactie == ModTranzactie.BANCOMAT) {
            suma *= 1.02; // comision 2%
        }
        if(suma > sold + limitaCredit) {
            System.out.println("Fonduri insuficiente. Limita de credit depasita");
            return;
        }
        super.extrage(suma, modTranzactie);
        adaugaTranzactie(new Tranzactie(TipTranzactie.EXTRAGE, modTranzactie, suma, new Date()));
    }

}

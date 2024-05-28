package com.banking.models;

import com.banking.enums.ModTranzactie;

import java.time.LocalDate;
import java.util.Date;

public class CardDebit extends CardBancar {

    private double limitaExtragere;

    public CardDebit(String numarCard, String numeProprietar, Date dataExpirarii, double sold, double limitaExtragere) {
        super(numarCard, numeProprietar, sold, dataExpirarii);
        this.limitaExtragere = limitaExtragere;
    }

    @Override
    public void extrage(double suma, ModTranzactie modTranzactie) {
        if (suma + sumaExtrasaAstazi() > limitaExtragere) {
            System.out.println("Limita de extragere zilnica depasita :( ");
            return;
        }
        super.extrage(suma, modTranzactie);
    }

    private double sumaExtrasaAstazi(){
        double sumaExtrasa = 0.0;

        for(Tranzactie tran : this.getTranzactii()) {
            if(tran.getData().equals(LocalDate.now()))
                sumaExtrasa += tran.getSuma();
        }

        return sumaExtrasa;
    }
}

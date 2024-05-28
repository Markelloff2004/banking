package com.banking.models;

import com.banking.enums.ModTranzactie;
import com.banking.enums.TipTranzactie;

import java.util.*;

public abstract class CardBancar {

    private static final Set<ModTranzactie> MODURI_PERMISE_EXTRAGERE = EnumSet.of(ModTranzactie.BANCOMAT,
            ModTranzactie.BANCA,ModTranzactie.TERMINAL, ModTranzactie.TRANSFER);
    private static final Set<ModTranzactie> MODURI_PERMISE_SUPLINIRE = EnumSet.of(ModTranzactie.BANCOMAT,
            ModTranzactie.BANCA, ModTranzactie.TRANSFER);


    protected String numarCard;
    protected String numeProprietar;
    protected double sold;
    protected Date dataExpirarii;
    protected List<Tranzactie> tranzactii;

    public CardBancar(String numarCard, String numeProprietar, double sold, Date dataExpirarii) {
        this.numarCard = numarCard;
        this.numeProprietar = numeProprietar;
        this.sold = sold;
        this.dataExpirarii = dataExpirarii;
        this.tranzactii = new ArrayList<>();
    }

    public void suplinire(double suma, ModTranzactie mod) {
        if (!MODURI_PERMISE_SUPLINIRE.contains(mod)) {
            System.out.println("Modul de tranzactie nu este permis pentru SUPLINIRE! ");
            return;
        }
        if(mod == ModTranzactie.BANCOMAT && suma % 10 == 0) {
            System.out.println("Pentru a EXTRAGE prin BANCOMAT - suma trebuie sa fie divizibila cu 10.");
            return;
        }
        sold += suma;
        adaugaTranzactie(new Tranzactie(TipTranzactie.SUPLINIRE, mod, suma, new Date()));
    };
    public void extrage(double suma, ModTranzactie mod) {
        if(!MODURI_PERMISE_EXTRAGERE.contains(mod)) {
            System.out.println("Modul de tranzactie nu este permis pentru EXTRAGERE! ");
            return;
        }
        if((mod == ModTranzactie.BANCOMAT) && (suma % 10 != 0)) {
            System.out.println("Pentru a EXTRAGE prin BANCOMAT - suma trebuie sa fie divizibila cu 10.");
            return;
        }
        sold -= suma;
        adaugaTranzactie(new Tranzactie(TipTranzactie.EXTRAGE, mod, suma, new Date()));
    };
    public void transfera(CardBancar cardDestinatie, double suma) {
        if(suma > sold) {
            System.out.println("Funduri insuficiente.");
            return;
        }
        sold -= suma;
        cardDestinatie.suplinire(suma, ModTranzactie.TRANSFER);
        adaugaTranzactie(new Tranzactie(TipTranzactie.TRANSFERA, ModTranzactie.TRANSFER, suma, new Date()));
    }
//    public abstract void tranzactie(TipTranzactie tip, double suma, CardBancar cardDestinatie);

    public void adaugaTranzactie(Tranzactie tranzactie) {
        tranzactii.add(tranzactie);
    }

    public  void miniExtras() {
        System.out.println("Numar Card : " + numarCard);
        System.out.println("Nume Proprietar : " + numeProprietar);
        System.out.println("Sold Curent : " + sold);
        System.out.println("Data Expirarii : " + dataExpirarii);
    }

    public void istoricTranzactii() {
        for (Tranzactie tran : tranzactii) {
            System.out.println(tran);
        }
    }

    public String getNumarCard() {
        return numarCard;
    }

    public void setNumarCard(String numarCard) {
        this.numarCard = numarCard;
    }

    public String getNumeProprietar() {
        return numeProprietar;
    }

    public void setNumeProprietar(String numeProprietar) {
        this.numeProprietar = numeProprietar;
    }

    public double getSold() {
        return sold;
    }

    public void setSold(double sold) {
        this.sold = sold;
    }

    public Date getDataExpirarii() {
        return dataExpirarii;
    }

    public void setDataExpirarii(Date dataExpirarii) {
        this.dataExpirarii = dataExpirarii;
    }

    public List<Tranzactie> getTranzactii() {
        return tranzactii;
    }

    public void setTranzactii(List<Tranzactie> tranzactii) {
        this.tranzactii = tranzactii;
    }
}

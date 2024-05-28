package com.banking.models;

import com.banking.enums.ModTranzactie;
import com.banking.enums.TipTranzactie;

import java.util.ArrayList;
import java.util.List;

public class Proprietar {

    private String nume;
    private List<CardBancar> carduri;

    public Proprietar(String nume) {
        this.nume = nume;
        this.carduri = new ArrayList<>();
    }

    public void adaugaCard(CardBancar card) {
        carduri.add(card);
    }

    public void efectueazaTranzactie(TipTranzactie tip, double suma, CardBancar cardSursa, CardBancar cardDestinatie, ModTranzactie modTranzactie) {
        switch (tip) {
            case EXTRAGE:
                cardSursa.extrage(suma, modTranzactie);
                break;
            case SUPLINIRE:
                cardSursa.suplinire(suma, modTranzactie);
                break;
            case TRANSFERA:
                if (cardDestinatie != null) {
                    cardSursa.transfera(cardDestinatie, suma);
                } else {
                    System.out.println("Nu ati indicat cardul destinatie.");
                }
                break;
            default:
                System.out.println("Ati ales o tranzactie necunoscuta.");
        }
    }

    public void afiseazaMiniExtras(CardBancar cardBancar) {
        cardBancar.miniExtras();
    }

    public void istoricTranzactii(CardBancar cardBancar) {
        cardBancar.istoricTranzactii();
    }

    public  void afiseazaMiniExtrasToateCardurile() {
        for (CardBancar card : carduri) {
            card.miniExtras();
        }
    }

    public  void afiseazaIstoricTranzactiiToateCardurile() {
        for (CardBancar card : carduri) {
            card.istoricTranzactii();
        }
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public List<CardBancar> getCarduri() {
        return carduri;
    }

    public void setCarduri(List<CardBancar> carduri) {
        this.carduri = carduri;
    }
}

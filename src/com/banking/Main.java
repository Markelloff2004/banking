package com.banking;

import com.banking.enums.ModTranzactie;
import com.banking.enums.TipTranzactie;
import com.banking.models.CardBancar;
import com.banking.models.CardCredit;
import com.banking.models.CardDebit;
import com.banking.models.Proprietar;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        cal.set(2024, Calendar.MAY, 19);
        Date dateExpirarii = cal.getTime();

        CardBancar cardDebitPaynet = new CardDebit("123456789", "Starsii Marcu", dateExpirarii, 1000, 200 );
        CardBancar cardCreditEximbank = new CardCredit("987654321","Starsii Marcu", dateExpirarii, 1000, 2000);
        CardBancar cardDebitMaib = new CardDebit("123459876", "Starsii Marcu", dateExpirarii, 5000, 1000 );

        Proprietar marcu = new Proprietar("Starsii Marcu");

        marcu.adaugaCard(cardDebitMaib);
        marcu.adaugaCard(cardDebitPaynet);
        marcu.adaugaCard(cardCreditEximbank);

        System.out.println("\n\n");

        //limita extragere zilnica depasita
        marcu.efectueazaTranzactie(TipTranzactie.EXTRAGE, 300, cardDebitPaynet, null, ModTranzactie.BANCOMAT);
        System.out.println();

        //tot normal
        marcu.efectueazaTranzactie(TipTranzactie.EXTRAGE, 100, cardDebitPaynet, null, ModTranzactie.BANCOMAT);
        System.out.println();

        //mod tranzactie nepotrivit pentru SUPLINIRE
        marcu.efectueazaTranzactie(TipTranzactie.SUPLINIRE, 200, cardDebitMaib, null, ModTranzactie.TERMINAL);
        System.out.println();

        //tot normal
        marcu.efectueazaTranzactie(TipTranzactie.SUPLINIRE, 200, cardDebitMaib, null, ModTranzactie.BANCA);
        System.out.println();

        //imposibil de extra asa suma prin bancomat
        marcu.efectueazaTranzactie(TipTranzactie.EXTRAGE, 205, cardDebitMaib, null, ModTranzactie.BANCOMAT);
        System.out.println();

        marcu.efectueazaTranzactie(TipTranzactie.TRANSFERA, 100, cardCreditEximbank, cardDebitPaynet, ModTranzactie.BANCOMAT);
        System.out.println();

        marcu.afiseazaMiniExtras(cardDebitPaynet);
        marcu.afiseazaMiniExtras(cardCreditEximbank);
        marcu.afiseazaMiniExtras(cardDebitMaib);
        System.out.println();

        marcu.istoricTranzactii(cardCreditEximbank);
        marcu.istoricTranzactii(cardDebitMaib);
        marcu.istoricTranzactii(cardDebitPaynet);
        System.out.println();

        marcu.afiseazaIstoricTranzactiiToateCardurile();
        System.out.println();

        marcu.afiseazaMiniExtrasToateCardurile();
        System.out.println();

    }
}
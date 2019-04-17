package com.egs;

import com.egs.customexceptions.MandatoryFieldException;
import com.egs.model.Atm;
import com.egs.model.Card;
import com.egs.model.Person;
import com.egs.service.InitService;

public class Starter {

    public static void main(String[] args) {


        InitService initService = new InitService();
        try {
            initService.initialize();
        } catch (MandatoryFieldException e) {
            e.printStackTrace();
        }

        Atm atm = new Atm();


        Thread firstThread = new Thread(() -> {

            Person sergey = initService.getRandomPerson();

            Card theCard = null;

            try {
                 theCard = sergey.getRandomCard();
            } catch (MandatoryFieldException e) {
                e.printStackTrace();
            }
            atm.getCash(theCard,2000);
        });


        Thread secondThread = new Thread(() -> {

            Person hakob = initService.getRandomPerson();

            Card theCard = null;

                try {
                theCard = hakob.getRandomCard();
            } catch (MandatoryFieldException e) {
                e.printStackTrace();
            }
                atm.getCash(theCard,1200);
        });

        firstThread.setName("Sergey");
        secondThread.setName("Hakob");
        secondThread.start();
        firstThread.start();


    }
}

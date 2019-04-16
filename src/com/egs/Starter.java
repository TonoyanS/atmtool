package com.egs;

import com.egs.customexceptions.MandatoryFieldException;
import com.egs.model.Atm;
import com.egs.model.Card;
import com.egs.model.Person;
import com.egs.service.InitService;

public class Starter {

    public static void main(String[] args) {

        Object test =  new Object();

        InitService initService = new InitService();
        try {
            initService.initialize();
        } catch (MandatoryFieldException e) {
            e.printStackTrace();
        }

        Atm atm = new Atm();


        Thread firstThread = new Thread(new Runnable() {
            @Override
            public void run() {

                Person sergey = initService.getRandomPerson();

                Card theCard = null;

//                synchronized (test) {
                try {
                     theCard = sergey.getRandomCard();
                } catch (MandatoryFieldException e) {
                    e.printStackTrace();
                }
                atm.getCash(theCard,2000);
//                test.notify();
//                }
            }
        });


        Thread secondThread = new Thread(new Runnable() {
            @Override
            public void run() {

                Person ako = initService.getRandomPerson();

                Card theCard = null;

                    try {
//                        test.wait();
                    theCard = ako.getRandomCard();
                } catch (MandatoryFieldException e) {
                    e.printStackTrace();
                }
                    atm.getCash(theCard,1200);
            }
        });

        firstThread.setName("Sergey");
        secondThread.setName("Ako");
        secondThread.start();
        firstThread.start();


    }
}

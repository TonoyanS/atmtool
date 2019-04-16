package com.egs.service;

import com.egs.model.Bank;
import com.egs.enums.IssuerBank;
import com.egs.model.Card;
import com.egs.logging.ConsoleLogger;

public class AtmService {

   private final static ConsoleLogger LOGGER = new ConsoleLogger(AtmService.class.getName());

    public boolean request (Card theCard, double amount) {


        String bankName = theCard.getIssuerBank();
        Bank theBank = IssuerBank.valueOf(bankName).getInstance();

        LOGGER.debug("Sending request to " + bankName);

        if (theBank.reduceMoney(theCard,amount)) {
            return true;
        }
        return false;
    }
}



package com.egs.service;

import com.egs.bankmodel.Bank;
import com.egs.bankmodel.IssuerBank;
import com.egs.cardmodel.Card;
import com.egs.logging.ConsoleLogger;
import com.egs.model.*;
import java.util.*;

public class AtmService {

    ConsoleLogger logger = new ConsoleLogger();

    // Sends request to bank
    public boolean request(Card theCard, double amount) {

        String bankName = theCard.getIssuerBank();
        String cardNumber = theCard.getCardNumber();
        long cardHolderId = theCard.getPersonID();

        logger.info("Sending request to " + bankName + " to withdraw money: " + amount);

        Bank theBank = IssuerBank.valueOf(bankName).getInstance();

        Set<Account> setAccounts = null;

        // Checking if the selected bank have customer with selected ID
        if (theBank.getAccountHolderData().containsKey(cardHolderId)) {
            setAccounts = theBank.getAccountHolderData().get(cardHolderId);
            logger.info("Successfully connected to bank");
        } else {
            logger.info("You haven't registered on any bank");
            return false;
        }

        boolean accountExists = false;
        Account authAccount = null;

        // Checking if our customer had account connected with card
        for (Account account:setAccounts) {

            if (account.getCardNumber().equals(cardNumber)) {

                accountExists = true;
                authAccount = account;
                logger.info("You have account connected with your card");
                break;
            }
        }

        // if account connected with card exists check account balance
        if (accountExists) {

            // if customer has enough money on his account, deducting money and returning true
            if (authAccount.getBalance() >= amount) {

           authAccount.setBalance(authAccount.getBalance() - amount);
           logger.info("You have enough money on your account, balance after transfer: " + authAccount.getBalance());
           return true;
            } else {
                logger.info("Not enough money in your account");
            }
        } else {

            logger.info("You haven't account connected with your card");
        }

        return false;
    }
}

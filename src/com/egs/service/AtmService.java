package com.egs.service;

import com.egs.model.Bank;
import com.egs.enums.IssuerBank;
import com.egs.model.Card;
import com.egs.logging.ConsoleLogger;
import com.egs.model.*;
import java.util.*;

public class AtmService {

   private final static ConsoleLogger logger = new ConsoleLogger();
   private Set<Account> setAccounts = null;
   private Account authAccount = null;

   private boolean checkBank (String bankName, long cardHolderId) {

       Bank theBank = IssuerBank.valueOf(bankName).getInstance();

       // Checking if the selected bank have customer with selected ID
       if (theBank.getAccountHolderData().containsKey(cardHolderId)) {

           setAccounts = theBank.getAccountHolderData().get(cardHolderId);
           logger.debug("Successfully connected to bank");
           return true;
       } else {
           logger.debug("Customer haven't accounts in bank");
           return false;
       }
   }


   private boolean checkAccount (String cardNumber) {

       boolean accountExists = false;
       // Checking if our customer had account connected with card
       for (Account account:setAccounts) {

           if (account.getCardNumber().equals(cardNumber)) {

               authAccount = account;
               logger.debug("Customer have account connected with your card");
               accountExists = true;
               break;
           } else {

               logger.debug("Customer haven't account connected with card");
           }
       }
       return accountExists;
   }



    // Sends request to bank
    public boolean request(Card theCard, double amount) {

        String bankName = theCard.getIssuerBank();
        String cardNumber = theCard.getCardNumber();
        long cardHolderId = theCard.getPersonID();

        logger.debug("Sending request to " + bankName + " to withdraw money: " + amount);

        // if account connected with card exists check account balance
        if (checkBank(bankName, cardHolderId) && checkAccount(cardNumber)) {

                // if customer has enough money on his account, deducting money and returning true
                if (authAccount.getBalance() >= amount) {

                    authAccount.setBalance(authAccount.getBalance() - amount);
                    logger.debug("Customer have enough money on account, balance after transfer: " + authAccount.getBalance());
                    return true;

                } else {
                    logger.debug("Not enough money in account");
                    System.out.println("Not enough money in your account");
                }
            }

            return false;
        }
    }



package com.egs.model;

import com.egs.bankmodel.Bank;
import com.egs.bankmodel.IssuerBank;
import com.egs.cardmodel.Card;
import com.egs.logging.ConsoleLogger;
import com.egs.parser.AccountParser;
import com.egs.parser.CardParser;
import com.egs.service.AtmService;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class Atm {

    ConsoleLogger logger = new ConsoleLogger();
    AtmService service = new AtmService();
    Card theCard = null;

    private double balance = 10000;



    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


    // Method which parses card.xml and account.xml and returns a random card
    public Card load () {
        ConsoleLogger logger = new ConsoleLogger();

        CardParser parser = new CardParser();
        AccountParser accountParser = new AccountParser();

        Bank theBank;
        Account account;

        // Parsing accounts from xml and creating account holder's data in banks
        List<Account> accountList;

        accountList = accountParser.parse("D:\\Tonoyan S\\Programming\\Projects\\Atm\\src\\com\\egs\\accounts.xml");

        for (int i = 0; i < accountList.size(); i++) {

            account = accountList.get(i);

            if (account.validateForNulls(account))
                System.exit(0);


            theBank = IssuerBank.valueOf(account.getIssuerBank()).getInstance();

            theBank.addBankCustomerAccount(theBank,account.getHolderID(),account);

        }
        logger.info("Accounts have been added");

        // Parsing cards and taking random one
        int a = (int) (Math.random() * 3);

        try {
            theCard = parser.parse("D:\\Tonoyan S\\Programming\\Projects\\Atm\\src\\com\\egs\\cards.xml").get(a);

            if (!theCard.validateForNulls(theCard)) {
                logger.info("Card is taken, ID: " + a);
            } else {
                System.exit(0);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return theCard;
    }

    // Validating ATM balance
    public boolean validateBalance (double amount) {

        if (amount <= this.balance) {
            logger.info("ATM have enough money");
            return true;
        }
        return false;
    }

    // Validating cards expiry date
    public boolean validateExpiryDate (Card card) {

        Date currentDate = new Date();

        if (currentDate.after(card.getExpiryDate())) {

            return false;
        }

        logger.info("Card is in date");
        return true;
    }

    // Method which gets money from ATM
    public void getCash(double amount) {

        if (validateBalance(amount)) {
            load();

            if (validateExpiryDate(theCard)) {

                if (service.request(theCard,amount)) {
                    balance = balance - amount;
                    System.out.println("Take your money: " + amount);
                }
            }
        }
    }
}

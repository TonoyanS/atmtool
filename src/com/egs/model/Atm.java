package com.egs.model;

import com.egs.enums.IssuerBank;
import com.egs.logging.ConsoleLogger;
import com.egs.parser.AccountParser;
import com.egs.parser.CardParser;
import com.egs.service.AtmService;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class Atm {

    private final static ConsoleLogger logger = new ConsoleLogger();
    private final static AtmService service = new AtmService();
    private final static CardParser parser = new CardParser();
    private final static AccountParser accountParser = new AccountParser();
    private static List<Card> cardList;
    Card theCard = null;

    private double balance;

    public Atm() {
        balance = 10000;
        loadAccounts();
        parseCards();
    }

    // create method which return instance of this class
    // we will discuss it next time
    // prepare your questions

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void loadAccounts () {

        Bank theBank;
        Account account;

        // Parsing accounts from xml and creating account holder's data in banks
        List<Account> accountList;

        accountList = accountParser.parse("accounts.xml");

        for (int i = 0; i < accountList.size(); i++) {

            account = accountList.get(i);
            account.validate();

            theBank = IssuerBank.valueOf(account.getIssuerBank()).getInstance();
            theBank.addBankCustomerAccount(theBank,account.getHolderID(),account);
        }
        logger.debug("Accounts have been added");
    }

    public void parseCards () {

        // Parsing cards and taking random one
        try {

            cardList = parser.parse("cards.xml");

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    // Getting a random card
    public Card getRandomCard () {

        int a = (int) (Math.random() * 3);

        theCard = cardList.get(a);

        theCard.validate();

        logger.debug("Card is taken, ID: " + a);

        return theCard;
    }



    // Validating ATM balance
    public boolean validateBalance (double amount) {

        if (amount <= this.balance) {
            logger.debug("ATM have enough money");
            return true;
        } else {
            logger.debug("ATM haven't enough money");
            System.out.println("Service problem");
        }
        return false;
    }

    // Validating cards expiry date
    public boolean validateExpiryDate (Card card) {

        Date currentDate = new Date();

        if (currentDate.after(card.getExpiryDate())) {

            logger.debug("Card date is expired");
            System.out.println("Your card is expired");
            return false;
        }

        logger.debug("Card is on term");
        return true;
    }

    // Method which gets money from ATM
    public void getCash(double amount) {

        if (validateBalance(amount)) {
            getRandomCard();
            if (validateExpiryDate(theCard)) {

                if (service.request(theCard,amount)) {
                    balance = balance - amount;
                    System.out.println("Take your money: " + amount);
                }
            }
        }
    }
}

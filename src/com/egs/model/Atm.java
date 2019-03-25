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
    
    // static final, load once use whenever you want
    ConsoleLogger logger = new ConsoleLogger();
    // same
    AtmService service = new AtmService();
    Card theCard = null;

    private double balance = 10000;
    
    // you can create private consturctor,
    // initialize balance inside constructor
    // create method which return instance of this class
    // we will discuss it next time
    // prepare your questions



    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


    // Method which parses card.xml and account.xml and returns a random card
    // In order to get random card you shold pare xml each time, this is not good idea
    // READ SOLID - Single responsibility
    // And next time tell me why this is a not good approach
    public Card load () {
        ConsoleLogger logger = new ConsoleLogger();

        CardParser parser = new CardParser();
        AccountParser accountParser = new AccountParser();

        Bank theBank;
        Account account;

        // Parsing accounts from xml and creating account holder's data in banks
        List<Account> accountList;
       // I hate this approach, what if I have to test you task in Linux and I have to run your project under linux
       // I am forced to change the path, because I don't have D:\\ .. in linux
       // Use classLoader and load resource as a stream
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
    
    // you can create private method in order to get file, and remove duplicates
    // if you need to use it inside other classes, you can create FileReaderManager for example
}

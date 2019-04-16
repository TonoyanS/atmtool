package com.egs.model;

import com.egs.logging.ConsoleLogger;
import com.egs.service.AtmService;

public class Atm {

    private final static ConsoleLogger LOGGER = new ConsoleLogger(Atm.class.getName());
    private final AtmService ATM_SERVICE = new AtmService();


    private double balance;

    public Atm() {
        balance = 10000;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    // Validating ATM balance
    public boolean validateBalance (double amount) {

        if (amount <= this.balance) {
            LOGGER.debug("ATM have enough money");
            return true;
        } else {
            LOGGER.debug("ATM haven't enough money");
            System.out.println("Service problem");
        }
        return false;
    }


    // Method which gets money from ATM
    public void getCash(Card theCard, double amount) {


            if (validateBalance(amount)) {

                if (ATM_SERVICE.request(theCard, amount)) {
                    balance = balance - amount;
                    System.out.println("Take your money: " + amount);
                }
            }
        }
    }

package com.egs.model;

import com.egs.customexceptions.MandatoryFieldException;
import com.egs.logging.ConsoleLogger;
import com.egs.validation.Validation;
import java.lang.reflect.Field;

public class Account implements Validation {

    // Why do you use holderId? You have used it on Bank side as a key of map

    // The AccountHolder's id, who owns this account
    private final long holderID;

    // The bank where is registered account
    private final String issuerBank;

    // Card number that is connected with this account
    private final String cardNumber;

    // Account's current balance
    private double balance;

    private final static ConsoleLogger logger = new ConsoleLogger();

    public long getHolderID() {
        return holderID;
    }

    public String getIssuerBank() {
        return issuerBank;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    // Constructor for Account
    public Account (long holderID, String issuerBank, String cardNumber, double balance) {

        this.holderID = holderID;
        this.issuerBank = issuerBank;
        this.cardNumber = cardNumber;
        this.balance = balance;

    }

    // Validate implementation for Account
    @Override
    public void validate() {

        // Setting class fields to array
        final Field[] declaredFields = this.getClass().getDeclaredFields();

        for(final Field field : declaredFields) {

            try {

                // if our field is null or empty throwing MandatoryField Exception
                if (field.get(this) == null || field.get(this).equals("")) {
                    try {
                        throw new MandatoryFieldException(this.getClass().getName()+ "." + field.getName());
                    }
                    catch (final MandatoryFieldException e) {
                        logger.error("Field is Null or Empty",e);
                        System.exit(0);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return "Account - " +
                "Holder ID: " + holderID +
                ", issuer bank: " + issuerBank +
                ", card number: " + cardNumber +
                ", balance: " + balance;
    }
}

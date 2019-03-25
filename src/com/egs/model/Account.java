package com.egs.model;

import com.egs.customexceptions.MandatoryFieldException;
import com.egs.validation.Validation;
import java.lang.reflect.Field;

public class Account implements Validation {

    // Why do you use holderId? You have used it on Bank side as a key of map
    // The AccountHolder's id, who owns this account
    private long holderID;

    // The bank where is registered account
    private String issuerBank;

    // Card number that is connected with this account
    private String cardNumber;

    // Account's current balance
    private double balance;

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
    public boolean validateForNulls(Object objectToValidate) {

        boolean isNull = false;

        // Setting class fields to array
        //final Field[] decalredFields
        // don't use reflexin
        Field[] declaredFields = objectToValidate.getClass().getDeclaredFields();

        // final Field field
        for(Field field : declaredFields) {

            try {

                // if our field is null or empty throwing MandatoryField Exception
                if (field.get(objectToValidate) == null || field.get(objectToValidate).equals("")) {
                    try {
                        isNull = true;
                        throw new MandatoryFieldException(objectToValidate.getClass().getName()+"."+field.getName());
                    } 
                    // final MandatoryFieldException e
                    catch (MandatoryFieldException e) {
                        // use logger
                        e.printStackTrace();
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return isNull;
    }

}

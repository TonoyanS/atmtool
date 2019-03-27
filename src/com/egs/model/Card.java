package com.egs.model;

import com.egs.enums.CardBrand;
import com.egs.enums.Currency;
import com.egs.customexceptions.MandatoryFieldException;
import com.egs.logging.ConsoleLogger;
import com.egs.validation.Validation;
import java.lang.reflect.Field;
import java.util.Date;

public class Card implements Validation {

    private final long personID;
    private final String cardNumber;
    private final CardBrand cardBrand;
    private final Currency currency;
    private final String issuerBank;
    private final String cardHolder;
    private final Date expiryDate;

    final static ConsoleLogger logger = new ConsoleLogger();

    // Constructor for card
    public Card(final CardBuilder cardBuilder) {
        this.personID = cardBuilder.getPersonID();
        this.cardNumber = cardBuilder.getCardNumber();
        this.cardBrand = cardBuilder.getCardBrand();
        this.currency = cardBuilder.getCurrency();
        this.issuerBank = cardBuilder.getIssuerBank();
        this.cardHolder = cardBuilder.getCardHolder();
        this.expiryDate = cardBuilder.getExpiryDate();

    }

    public long getPersonID() {
        return personID;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public CardBrand getCardBrand() {
        return cardBrand;
    }

    public Currency getCurrency() {
        return currency;
    }

    public String getIssuerBank() {
        return issuerBank;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }


    // Validate implementation for Card
    @Override
    public void validate() {

        // Setting class fields to array
        final Field[] declaredFields = this.getClass().getDeclaredFields();

        for(final Field field : declaredFields) {

            try {

                // if our field is null or empty throwing MandatoryField Exception
                if (field.get(this) == null || field.get(this).equals("")) {
                    try {
                        throw new MandatoryFieldException(this.getClass().getName()+"."+field.getName());
                    }

                    catch (final MandatoryFieldException e) {
                        logger.error("Field is null or Empty",e);
                        System.exit(0);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }


    // Builder pattern for Card
    public static class CardBuilder {

        private long personID;
        private String cardNumber;
        private CardBrand cardBrand;
        private Currency currency;
        private String issuerBank;
        private String cardHolder;
        private Date expiryDate;

        // Good approach
        public CardBuilder personID (final long personID) {
            this.personID = personID;
            return this;
        }

        public CardBuilder cardNumber (final String cardNumber) {
            this.cardNumber = cardNumber;
            return this;
        }

        public CardBuilder cardBrand (final CardBrand cardBrand) {
            this.cardBrand = cardBrand;
            return this;
        }

        public CardBuilder currency (final Currency currency) {
            this.currency = currency;
            return this;
        }

        public CardBuilder issuerBank (final String issuerBank) {
            this.issuerBank = issuerBank;
            return this;
        }
        public CardBuilder cardHolder (final String cardHolder) {
            this.cardHolder = cardHolder;
            return this;
        }

        public CardBuilder expiryDate (final Date expiryDate) {
            this.expiryDate = expiryDate;
            return this;
        }

        public long getPersonID() {
        return personID;
    }

        public String getCardNumber() {
            return cardNumber;
        }

        public CardBrand getCardBrand() {
            return cardBrand;
        }

        public Currency getCurrency() {
            return currency;
        }

        public String getIssuerBank() {
            return issuerBank;
        }

        public String getCardHolder() {
            return cardHolder;
        }

        public Date getExpiryDate() {
            return expiryDate;
        }

        public Card build() {
            return new Card(this);
        }
    }

    @Override
    public String toString() {
        return "Card - " +
                "Person ID: " + personID +
                ", card number: " + cardNumber +
                ", card brand: " + cardBrand +
                ", currency: " + currency +
                ", issuer bank: " + issuerBank +
                ", card holder: " + cardHolder +
                ", expiry date: " + expiryDate;
    }
}



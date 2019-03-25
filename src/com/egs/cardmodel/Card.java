package com.egs.cardmodel;

import com.egs.customexceptions.MandatoryFieldException;
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
    public boolean validateForNulls(Object objectToValidate) {

        // Setting class fields to array
        Field[] declaredFields = objectToValidate.getClass().getDeclaredFields();
        boolean isNull = false;

        for(Field field : declaredFields) {

            try {
                // if our field is null or empty throwing MandatoryField Exception
                if (field.get(objectToValidate) == null || field.get(objectToValidate).equals("")) {
                    try {
                        isNull = true;
                        throw new MandatoryFieldException(objectToValidate.getClass().getName()+"."+field.getName());
                    } catch (MandatoryFieldException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return isNull;
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

}



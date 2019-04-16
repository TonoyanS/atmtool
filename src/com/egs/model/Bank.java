package com.egs.model;

import com.egs.logging.ConsoleLogger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Bank {

    private static final ConsoleLogger LOGGER = new ConsoleLogger(Bank.class.getName());

    // Map consisting of an holder and a set of his accounts
    private Map<Long, Set<Account>> accountHolderData;

    public Bank() {
        this.accountHolderData = new HashMap<>();
    }

    public void setAccountHolderData(Map<Long, Set<Account>> accountHolderData) {
        this.accountHolderData = accountHolderData;
    }

    public Map<Long, Set<Account>> getAccountHolderData() {
        return accountHolderData;
    }

    public boolean addBankCustomerAccount (Bank theBank, long accountHolderId, Account account) {

        if (!theBank.getAccountHolderData().containsKey(accountHolderId)) {

            Set<Account> accountSet = new HashSet<>();
            accountSet.add(account);
            theBank.getAccountHolderData().put(accountHolderId,accountSet);
        } else {

            theBank.getAccountHolderData().get(accountHolderId).add(account);
        }
        return true;
    }

    public boolean reduceMoney (Card theCard, double amount) {

        String cardNumber = theCard.getCardNumber();
        long cardHolderId = theCard.getPersonID();
        Set<Account> accountSet = checkCustomer(cardHolderId);


        if (accountSet != null) {
            Account authenticAccount = checkCustomerAccount(cardNumber,accountSet);
            if (authenticAccount != null) {

                if (authenticAccount.getBalance() >= amount) {

                    authenticAccount.setBalance(authenticAccount.getBalance() - amount);
                    LOGGER.debug("Customer have enough money on account, balance after transfer: " + authenticAccount.getBalance());
                    return true;

                } else {
                    LOGGER.debug("Not enough money in account");
                    System.out.println("Not enough money in your account");
                }
            }

        }
    return false;
    }

    private Set<Account> checkCustomer (long cardHolderId) {

        Set<Account> accountSet = null;

        // Checking if the selected bank have customer with selected ID
        if (getAccountHolderData().containsKey(cardHolderId)) {

            accountSet = getAccountHolderData().get(cardHolderId);
            LOGGER.debug("Successfully connected to bank");
            return accountSet;
        } else {
            LOGGER.debug("Customer haven't accounts in bank");
            return accountSet;
        }
    }

    private Account checkCustomerAccount (String cardNumber, Set<Account> accountSet) {

        Account foundAccount = null;

        // Checking if our customer had account connected with card
        for (Account account:accountSet) {

            if (account.getCardNumber().equals(cardNumber)) {

                foundAccount = account;
                LOGGER.debug("Customer have account connected with your card");
                return foundAccount;
            } else {

                LOGGER.debug("Customer haven't account connected with card");
            }
        }
        return foundAccount;
    }
}

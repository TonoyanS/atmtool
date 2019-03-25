package com.egs.bankmodel;

import com.egs.model.Account;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Bank {

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
}

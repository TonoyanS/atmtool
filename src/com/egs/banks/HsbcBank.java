package com.egs.banks;

import com.egs.model.Bank;

public class HsbcBank extends Bank {

    private static HsbcBank instance = null;

    private HsbcBank() {}

    public static synchronized Bank getInstance() {
        if (instance == null)
            instance = new HsbcBank();
        return instance;
    }
}

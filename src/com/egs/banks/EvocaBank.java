package com.egs.banks;

import com.egs.model.Bank;

public class EvocaBank extends Bank {

    private static EvocaBank instance = null;

    private EvocaBank() {}

    public static synchronized Bank getInstance() {
        if (instance == null)
            instance = new EvocaBank();
        return instance;
    }
}

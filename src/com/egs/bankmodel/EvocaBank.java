package com.egs.bankmodel;

public class EvocaBank extends Bank {

    private static EvocaBank instance = null;

    private EvocaBank() {}

    public static synchronized Bank getInstance() {
        if (instance == null)
            instance = new EvocaBank();
        return instance;
    }
}

package com.egs.bankmodel;

public final class AmeriaBank extends Bank {

    private static AmeriaBank instance = null;

    private AmeriaBank() {}

    public static synchronized Bank getInstance() {
        if (instance == null)
            instance = new AmeriaBank();
        return instance;
    }
}

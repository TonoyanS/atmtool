package com.egs.bankmodel;

public enum IssuerBank {

    AMERIA{
        @Override
        public Bank getInstance() {
            return AmeriaBank.getInstance();
        }
    }, HSBC{
        @Override
        public Bank getInstance() {
            return HsbcBank.getInstance();
        }
    }, EVOCA{
        @Override
        public Bank getInstance() {
            return EvocaBank.getInstance();
        }
    };

    public abstract Bank getInstance();
}

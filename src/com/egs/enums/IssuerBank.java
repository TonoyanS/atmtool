package com.egs.enums;

import com.egs.banks.AmeriaBank;
import com.egs.model.Bank;
import com.egs.banks.EvocaBank;
import com.egs.banks.HsbcBank;

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

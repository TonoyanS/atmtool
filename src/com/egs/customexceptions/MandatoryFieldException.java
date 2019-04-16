package com.egs.customexceptions;

public class MandatoryFieldException extends Exception {

        public MandatoryFieldException(String errorMessage) {
            super(errorMessage);
        }
}


package com.egs.customexceptions;

public class MandatoryFieldException extends Exception {
        // So? What is this? 
        private String fieldName;

        public MandatoryFieldException(String fieldName) {
            this.fieldName = fieldName;
        }

        // Not good, make a google how to create Custom expection
        @Override
        public String toString() {
            return this.getClass().getName() + "\n" + fieldName + " cannot be null";
        }

    }


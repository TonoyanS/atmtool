package com.egs.customexceptions;

public class MandatoryFieldException extends Exception {

        private String fieldName;

        public MandatoryFieldException(String fieldName) {
            this.fieldName = fieldName;
        }

        @Override
        public String toString() {
            return this.getClass().getName() + "\n" + fieldName + " cannot be null";
        }

    }


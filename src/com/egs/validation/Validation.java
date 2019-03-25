package com.egs.validation;

import com.egs.customexceptions.MandatoryFieldException;

public interface Validation<T> {

    // method does not need any argument, becuase you validate the instance of implementation of this interface.
    // return type could be void, becuase in case of invalid input you are going to throw MandatoryFieldException
    boolean validateForNulls(T objectToValidate) throws MandatoryFieldException;

}

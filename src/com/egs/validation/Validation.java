package com.egs.validation;

import com.egs.customexceptions.MandatoryFieldException;

public interface Validation<T> {

    boolean validateForNulls(T objectToValidate) throws MandatoryFieldException;

}

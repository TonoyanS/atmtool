package com.egs.validation;

import com.egs.customexceptions.MandatoryFieldException;

public interface Validation {

    void validate() throws MandatoryFieldException;

}

package com.joel.cdr.domain.usecase.cdr.create;

import com.joel.cdr.domain.usecase.cdr.model.ChargeDetailRecord;

public interface Create {

    ChargeDetailRecord execute(ChargeDetailRecord chargeDetailRecord);
}

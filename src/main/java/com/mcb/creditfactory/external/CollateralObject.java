package com.mcb.creditfactory.external;

import com.mcb.creditfactory.dto.Collateral;
import com.mcb.creditfactory.model.AssessedCollateral;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface CollateralObject {
    Long getId();
    BigDecimal getValue();
    Short getYear();
    LocalDate getDate();
    CollateralType getType();
    Collateral getCollateralObjectDTO();
    void setValue(BigDecimal value);
    void setDate(LocalDate date);
}

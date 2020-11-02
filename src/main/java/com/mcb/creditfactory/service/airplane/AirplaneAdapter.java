package com.mcb.creditfactory.service.airplane;

import com.mcb.creditfactory.dto.AirplaneDto;
import com.mcb.creditfactory.dto.Collateral;
import com.mcb.creditfactory.external.CollateralObject;
import com.mcb.creditfactory.external.CollateralType;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
public class AirplaneAdapter implements CollateralObject {
    private AirplaneDto airplane;

    @Override
    public Long getId() {
        return airplane.getId();
    }

    @Override
    public BigDecimal getValue() {
        return airplane.getValue();
    }

    @Override
    public Short getYear() {
        return airplane.getYear();
    }

    @Override
    public LocalDate getDate() {
        return airplane.getDate();
    }

    @Override
    public CollateralType getType() {
        return CollateralType.AIRPLANE;
    }

    @Override
    public Collateral getCollateralObjectDTO() {
        return airplane;
    }

    @Override
    public void setValue(BigDecimal value) {
        airplane.setValue(value);
    }

    @Override
    public void setDate(LocalDate date) {
        airplane.setDate(date);

    }


}

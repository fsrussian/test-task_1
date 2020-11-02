package com.mcb.creditfactory.service.car;

import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.dto.Collateral;
import com.mcb.creditfactory.external.CollateralObject;
import com.mcb.creditfactory.external.CollateralType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class CarAdapter implements CollateralObject {
    private CarDto car;


    @Override
    public Long getId() {
        return car.getId();
    }

    @Override
    public BigDecimal getValue() {
        return car.getValue();
    }

    @Override
    public Short getYear() {
        return car.getYear();
    }

    @Override
    public LocalDate getDate() {
        return car.getDate();
    }

    @Override
    public CollateralType getType() {
        return CollateralType.CAR;
    }

    @Override
    public Collateral getCollateralObjectDTO() {
        return car;
    }

    @Override
    public void setValue(BigDecimal value) {
        car.setValue(value);
    }

    @Override
    public void setDate(LocalDate date) {
        car.setDate(date);
    }


}

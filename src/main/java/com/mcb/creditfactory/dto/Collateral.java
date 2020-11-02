package com.mcb.creditfactory.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.mcb.creditfactory.external.CollateralObject;
import com.mcb.creditfactory.model.Airplane;
import com.mcb.creditfactory.visitor.CollateralVisitor;

import java.math.BigDecimal;
import java.time.LocalDate;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CarDto.class),
        @JsonSubTypes.Type(value = AirplaneDto.class)
})
public interface Collateral {
    CollateralObject accept(CollateralVisitor collateralVisitor);
}

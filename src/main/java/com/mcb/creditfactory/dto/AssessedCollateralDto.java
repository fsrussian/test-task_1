package com.mcb.creditfactory.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.mcb.creditfactory.external.CollateralType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeName("assessedValue")
public class AssessedCollateralDto {
    private Long id;
    private CollateralType collateralType;
    private long collateralId;
    private BigDecimal value;
    private LocalDate date;

    public AssessedCollateralDto(CollateralType collateralType, BigDecimal value, LocalDate date) {
        this.collateralType = collateralType;
        this.value = value;
        this.date = date;
    }
}

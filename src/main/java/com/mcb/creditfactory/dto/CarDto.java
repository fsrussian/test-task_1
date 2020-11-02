package com.mcb.creditfactory.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.mcb.creditfactory.external.CollateralObject;
import com.mcb.creditfactory.visitor.CollateralVisitor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("car")
public class CarDto implements Collateral {
    private Long id;
    private String brand;
    private String model;
    private Double power;
    private Short year;
    private BigDecimal value;
    private LocalDate date;

    public CarDto(Long id, String brand, String model, Double power, Short year) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.power = power;
        this.year = year;
    }

    @Override
    public CollateralObject accept(CollateralVisitor collateralVisitor) {
        return collateralVisitor.visitCar(this);
    }
}

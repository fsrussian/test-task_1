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
@JsonTypeName("airPlane")
public class AirplaneDto implements Collateral {
    private Long id;
    private String brand;
    private String model;
    private String manufacturer;
    private short year;
    private int fuelCapacity;
    private int seats;
    private BigDecimal value;
    private LocalDate date;

    public AirplaneDto(Long id, String brand, String model, String manufacturer, short year, int fuelCapacity, int seats) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.manufacturer = manufacturer;
        this.year = year;
        this.fuelCapacity = fuelCapacity;
        this.seats = seats;
    }

    @Override
    public CollateralObject accept(CollateralVisitor collateralVisitor) {
        return collateralVisitor.visitAirplane(this);
    }
}


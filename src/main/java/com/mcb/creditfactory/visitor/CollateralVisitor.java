package com.mcb.creditfactory.visitor;

import com.mcb.creditfactory.dto.AirplaneDto;
import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.external.CollateralObject;

public interface CollateralVisitor {
    CollateralObject visitAirplane(AirplaneDto collateralObject);
    CollateralObject visitCar(CarDto collateralObject);
}

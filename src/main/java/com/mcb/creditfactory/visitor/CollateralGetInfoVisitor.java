package com.mcb.creditfactory.visitor;

import com.mcb.creditfactory.dto.AirplaneDto;
import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.external.CollateralObject;
import com.mcb.creditfactory.external.CollateralType;
import com.mcb.creditfactory.model.AssessedCollateral;
import com.mcb.creditfactory.service.airplane.AirplaneAdapter;
import com.mcb.creditfactory.service.airplane.AirplaneService;
import com.mcb.creditfactory.service.assessedcollateral.AssessedCollateralService;
import com.mcb.creditfactory.service.car.CarAdapter;
import com.mcb.creditfactory.service.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

@SuppressWarnings("unchecked")
public class CollateralGetInfoVisitor implements CollateralVisitor {
    @Autowired
    private CarService carService;
    @Autowired
    private AirplaneService airplaneService;
    @Autowired
    private AssessedCollateralService assessedCollateralService;


    @Override
    public CollateralObject visitAirplane(AirplaneDto collateralObject) {
        AssessedCollateral lastAssessedCollateralFromDB = assessedCollateralService.getLastAssessedCollateralFromDB(CollateralType.AIRPLANE, collateralObject.getId());
        if (lastAssessedCollateralFromDB==null)
            return null;
        AirplaneDto airplaneDto = Optional.of(collateralObject)
                .map(airplaneService::fromDto)
                .map(airplaneService::getId)
                .flatMap(airplaneService::load)
                .map(airplaneService::toDTO)
                .orElse(null);

        airplaneDto.setValue(lastAssessedCollateralFromDB.getValue());
        airplaneDto.setDate(lastAssessedCollateralFromDB.getDate());
        return new AirplaneAdapter(airplaneDto);

    }

    @Override
    public CollateralObject visitCar(CarDto collateralObject) {
        AssessedCollateral lastAssessedCollateralFromDB = assessedCollateralService.getLastAssessedCollateralFromDB(CollateralType.CAR, collateralObject.getId());
        if (lastAssessedCollateralFromDB==null)
            return null;
        CarDto carDto = Optional.of(collateralObject)
                .map(carService::fromDto)
                .map(carService::getId)
                .flatMap(carService::load)
                .map(carService::toDTO)
                .orElse(null);
        carDto.setValue(lastAssessedCollateralFromDB.getValue());
        carDto.setDate(lastAssessedCollateralFromDB.getDate());
        return new CarAdapter(carDto);
    }
}

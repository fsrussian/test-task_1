package com.mcb.creditfactory.visitor;

import com.mcb.creditfactory.dto.AirplaneDto;
import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.external.CollateralObject;
import com.mcb.creditfactory.model.AssessedCollateral;
import com.mcb.creditfactory.service.airplane.AirplaneAdapter;
import com.mcb.creditfactory.service.airplane.AirplaneService;
import com.mcb.creditfactory.service.assessedcollateral.AssessedCollateralServiceImpl;
import com.mcb.creditfactory.service.car.CarAdapter;
import com.mcb.creditfactory.service.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CollateralSaveVisitor implements CollateralVisitor {
    @Autowired
    private CarService carService;
    @Autowired
    private AirplaneService airplaneService;
    @Autowired
    private AssessedCollateralServiceImpl assessedCollateralService;



    @Override
    public CollateralObject visitAirplane(AirplaneDto airPlane) {
        AssessedCollateral lastAssessedCollateral = assessedCollateralService.getLastAssessedCollateral(new AirplaneAdapter(airPlane));
        boolean approved = airplaneService.approve(airPlane);
        if (!approved) {
            return null;
        }
        Long id = Optional.of(airPlane)
                .map(airplaneService::fromDto)
                .map(airplaneService::save)
                .map(airplaneService::getId)
                .orElse(null);
        lastAssessedCollateral.setCollateralId(id);
        assessedCollateralService.addAssessedCollateral(lastAssessedCollateral);
        airPlane.setId(id);

        return new AirplaneAdapter(airPlane);
    }

    @Override
    public CollateralObject visitCar(CarDto car) {
        AssessedCollateral lastAssessedCollateral = assessedCollateralService.getLastAssessedCollateral(new CarAdapter(car));
        boolean approved = carService.approve(car);
        if (!approved) {
            return null;
        }
        Long id = Optional.of(car)
                .map(carService::fromDto)
                .map(carService::save)
                .map(carService::getId)
                .orElse(null);
        lastAssessedCollateral.setCollateralId(id);
        assessedCollateralService.addAssessedCollateral(lastAssessedCollateral);
        car.setId(id);
        return new CarAdapter(car);
    }
}

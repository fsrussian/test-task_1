package com.mcb.creditfactory.service.assessedcollateral;

import com.mcb.creditfactory.dto.AssessedCollateralDto;
import com.mcb.creditfactory.dto.Collateral;
import com.mcb.creditfactory.external.CollateralObject;
import com.mcb.creditfactory.external.CollateralType;
import com.mcb.creditfactory.external.ExternalApproveService;
import com.mcb.creditfactory.model.AssessedCollateral;
import com.mcb.creditfactory.repository.AssessedCollateralRepository;
import com.mcb.creditfactory.service.car.CarAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Service
public class AssessedCollateralServiceImpl implements AssessedCollateralService {

    @Autowired
    AssessedCollateralRepository assessedCollateralRepository;


    @Override
    public AssessedCollateralDto toDto(AssessedCollateral assessed) {
        return new AssessedCollateralDto(
                assessed.getId(),
                assessed.getCollateralType(),
                assessed.getCollateralId(),
                assessed.getValue(),
                assessed.getDate());
    }

    @Override
    public AssessedCollateral fromDto(AssessedCollateralDto assessedCollateralDto) {
        return new AssessedCollateral(
                assessedCollateralDto.getId(),
                assessedCollateralDto.getCollateralType(),
                assessedCollateralDto.getCollateralId(),
                assessedCollateralDto.getValue(),
                assessedCollateralDto.getDate()
        );
    }

    @Override
    public Long getId(AssessedCollateral assessedCollateral) {
        return assessedCollateral.getId();
    }

    @Override
    public AssessedCollateral addAssessedCollateral(AssessedCollateral assessedCollateral) {
        return assessedCollateralRepository.save(assessedCollateral);
    }

    @Override
    public AssessedCollateralDto getAssessedFromCollateralObject(CollateralObject collateralObject) {
        if (collateralObject.getValue() == null)
            return null;

        return new AssessedCollateralDto(collateralObject.getType(), collateralObject.getValue(), collateralObject.getDate());
    }

    public boolean checkAssessedValue(CollateralObject collateral) {
        if (collateral.getValue() != null) {
            addAssessedCollateral(
                    new AssessedCollateral(collateral.getType(),
                            collateral.getId(),
                            collateral.getValue(),
                            collateral.getDate() != null ? collateral.getDate() : LocalDate.now()
                    )
            );
            return true;
        }
        return false;
    }

    public AssessedCollateral getLastAssessedCollateralFromDB(CollateralType type, Long id) {
        if (id==null)
            return null;
        return assessedCollateralRepository.getTopByCollateralTypeAndCollateralIdOrderByDateDesc(type, id);
    }


    public AssessedCollateral getLastAssessedCollateral(CollateralObject carAdapter) {
        AssessedCollateral lastAssessedCollateral;
        if (carAdapter.getId() == null) {
            lastAssessedCollateral = new AssessedCollateral(carAdapter.getType(), carAdapter.getValue(),
                    carAdapter.getDate() != null ? carAdapter.getDate() : LocalDate.now());
        } else {
            checkAssessedValue(carAdapter);
             lastAssessedCollateral = getLastAssessedCollateralFromDB(carAdapter.getType(), carAdapter.getId());
            if (lastAssessedCollateral== null)
                return null;
        }
        carAdapter.setValue(lastAssessedCollateral.getValue());
        carAdapter.setDate(lastAssessedCollateral.getDate());
        return lastAssessedCollateral;




    }


}

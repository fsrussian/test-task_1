package com.mcb.creditfactory.service.assessedcollateral;

import com.mcb.creditfactory.dto.AssessedCollateralDto;
import com.mcb.creditfactory.dto.Collateral;
import com.mcb.creditfactory.external.CollateralObject;
import com.mcb.creditfactory.external.CollateralType;
import com.mcb.creditfactory.model.AssessedCollateral;

public interface AssessedCollateralService {
    AssessedCollateralDto toDto(AssessedCollateral assessed);
    AssessedCollateral fromDto(AssessedCollateralDto assessedCollateralDto);
    Long getId(AssessedCollateral assessedCollateral);
    AssessedCollateral addAssessedCollateral(AssessedCollateral assessedCollateral);
     AssessedCollateralDto getAssessedFromCollateralObject(CollateralObject collateralObject);

     boolean checkAssessedValue(CollateralObject collateral);
     AssessedCollateral getLastAssessedCollateralFromDB(CollateralType type, Long id);
}

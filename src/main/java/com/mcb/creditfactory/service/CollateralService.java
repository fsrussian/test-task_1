package com.mcb.creditfactory.service;

import com.mcb.creditfactory.dto.Collateral;
import com.mcb.creditfactory.external.CollateralObject;
import com.mcb.creditfactory.visitor.CollateralGetInfoVisitor;
import com.mcb.creditfactory.visitor.CollateralSaveVisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// TODO: reimplement this
@Service
public class CollateralService {

    @Autowired
    CollateralSaveVisitor collateralSaveVisitor;

    @Autowired
    CollateralGetInfoVisitor collateralGetInfoVisitor;

    @SuppressWarnings("ConstantConditions")
    public Long saveCollateral(Collateral objectCol) {
        CollateralObject collateralObject = objectCol.accept(collateralSaveVisitor);
        if (collateralObject==null)
            return null;
        return collateralObject.getId();

    }

    public Collateral getInfo(Collateral object) {
        CollateralObject collateralObject = object.accept(collateralGetInfoVisitor);
        if (collateralObject==null)
            return null;
        return collateralObject.getCollateralObjectDTO();

    }

}




package com.mcb.creditfactory.repository;

import com.mcb.creditfactory.external.CollateralType;
import com.mcb.creditfactory.model.AssessedCollateral;
import org.springframework.data.repository.CrudRepository;

public interface AssessedCollateralRepository extends CrudRepository<AssessedCollateral, Long> {
    AssessedCollateral getTopByCollateralTypeAndCollateralIdOrderByDateDesc(CollateralType collateral_type, long collateral_id);

}

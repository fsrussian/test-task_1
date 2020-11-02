package com.mcb.creditfactory.model;

import com.mcb.creditfactory.external.CollateralType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "ASSESSED_COLLATERAL")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssessedCollateral {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "collateral_type")
    CollateralType collateralType;

    @Column(name = "collateral_id")
    private Long collateralId;

    private BigDecimal value;
    private LocalDate date;

    public AssessedCollateral(CollateralType collateralType, Long collateralId, BigDecimal value, LocalDate date) {
        this.collateralType = collateralType;
        this.collateralId = collateralId;
        this.value = value;
        this.date = date;
    }

    public AssessedCollateral(CollateralType collateralType, BigDecimal value, LocalDate date) {
        this.collateralType = collateralType;
        this.value = value;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssessedCollateral that = (AssessedCollateral) o;
        return Objects.equals(id, that.id) &&
                collateralType == that.collateralType &&
                Objects.equals(collateralId, that.collateralId) &&
                Objects.equals(value, that.value) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, collateralType, collateralId, value, date);
    }
}

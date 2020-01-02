package pl.sidor.CarInsurancesSystem.entity.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.sidor.CarInsurancesSystem.entity.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarInsurance extends BaseEntity<Long> implements Serializable {

    private static final long serialVersionUID = 5907072156000469252L;

    @Column(name = "POLICY_NUMBER")
    private String policyNumber;

    @Column(name = "IS_COMPANY")
    private boolean isCompany;

    @Column(name = "DATE_FROM")
    private Date dateFrom;

    @Column(name = "DATE_TO")
    private Date dateTo;
}

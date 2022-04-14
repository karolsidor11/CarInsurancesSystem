package pl.sidor.CarInsurancesSystem.entity.entities;

import lombok.*;
import pl.sidor.CarInsurancesSystem.entity.base.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarInsurance extends BaseEntity<Long> implements Serializable {

    private static final long serialVersionUID = 5907072156000469252L;

    @Column(name = "POLICY_NUMBER")
    private String policyNumber;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "CAR_ID")
    private Car car;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "PERSON_ID")
    private Person person;

    @Column(name = "DATE_FROM")
    private Date dateFrom;

    @Column(name = "DATE_TO")
    private Date dateTo;
}

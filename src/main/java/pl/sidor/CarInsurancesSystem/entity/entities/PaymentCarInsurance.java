package pl.sidor.CarInsurancesSystem.entity.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.sidor.CarInsurancesSystem.entity.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentCarInsurance extends BaseEntity<Long> implements Serializable {

    private static final long serialVersionUID = 7550741654856287486L;

    @Column(name = "POLICY_NUMBER")
    private String policyNumber;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "MARK")
    private String mark;

    @Column(name = "MODEL")
    private String model;

    @Column(name = "REGISTRY_NUMBER")
    private String registryNumber;
}

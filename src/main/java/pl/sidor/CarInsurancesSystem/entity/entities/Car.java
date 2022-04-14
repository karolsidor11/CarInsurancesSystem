package pl.sidor.CarInsurancesSystem.entity.entities;

import lombok.*;
import pl.sidor.CarInsurancesSystem.entity.base.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Car extends BaseEntity<Long> implements Serializable {

    private static final long serialVersionUID = 773053417852664574L;

    @Column(name = "MARK")
    private String mark;

    @Column(name = "MODEL")
    private String model;

    @Column(name = "REGISTRY_NUMBER")
    private String registryNumber;

    @Column(name = "CAPACITY")
    private Double capacity;

    @Column(name = "POWER")
    private Integer power;

    @Column(name = "PRODUCTION_YEAR")
    private int productionYear;

    @Column(name = "FUEL")
    private String  fuel;

    @OneToOne()
    @JoinColumn(name = "PERSON_ID")
    private Person person;
}

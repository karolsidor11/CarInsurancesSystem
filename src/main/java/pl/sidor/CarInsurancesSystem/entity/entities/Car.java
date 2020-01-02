package pl.sidor.CarInsurancesSystem.entity.entities;

import lombok.*;
import pl.sidor.CarInsurancesSystem.entity.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Car extends BaseEntity<Long> implements Serializable {

    private static final long serialVersionUID = 773053417852664574L;

    @Column(name = "MARK")
    private String mark;

    @Column(name = "MODEL")
    private String model;

    @Column(name = "CAPACITY")
    private Double capacity;

    @Column(name = "POWER")
    private Integer power;

    @Column(name = "PRODUCTION_YEAR")
    private int productionYear;

    @Column(name = "FUEL")
    private String  fuel;
}

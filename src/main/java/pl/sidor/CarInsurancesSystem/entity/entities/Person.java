package pl.sidor.CarInsurancesSystem.entity.entities;


import lombok.*;
import pl.sidor.CarInsurancesSystem.entity.base.BaseEntity;
import pl.sidor.CarInsurancesSystem.entity.embeded.Adres;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person extends BaseEntity<Long> implements Serializable {

    private static final long serialVersionUID = 6679878500355569583L;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "PESEL")
    private Integer pesel;

    @Embedded
    private Adres adres;

    @OneToOne(mappedBy = "person")
    private Car car;

}

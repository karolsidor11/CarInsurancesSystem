package pl.sidor.CarInsurancesSystem.entity.entities;

import lombok.*;
import pl.sidor.CarInsurancesSystem.entity.base.BaseEntity;
import pl.sidor.CarInsurancesSystem.entity.embeded.Adres;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Company extends BaseEntity<Long> implements Serializable {

    private static final long serialVersionUID = -4977725439935379112L;

    @Column(name = "COMPANY_NAME")
    private String companyName;

    @Column(name = "NIP")
    private Integer nip;

    @Column(name = "REGON")
    private Integer regon;

    @Embedded
    private Adres adres;

}

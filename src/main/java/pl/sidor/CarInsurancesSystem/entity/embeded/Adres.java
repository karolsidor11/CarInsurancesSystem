package pl.sidor.CarInsurancesSystem.entity.embeded;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class Adres implements Serializable {

    private static final long serialVersionUID = -8873553525883565572L;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "CITY")
    private String city;

    @Column(name = "ZIPCODE")
    private String zipCode;

    @Column(name = "STREET")
    private String street;
}

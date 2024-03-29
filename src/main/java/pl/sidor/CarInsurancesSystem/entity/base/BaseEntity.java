package pl.sidor.CarInsurancesSystem.entity.base;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity<PK extends Serializable> implements Serializable {

    private static final long serialVersionUID = -8790810775374073369L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private PK id;
}

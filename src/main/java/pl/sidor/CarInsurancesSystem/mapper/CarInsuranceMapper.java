package pl.sidor.CarInsurancesSystem.mapper;

import generated_class.model.CarInsuranceRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.sidor.CarInsurancesSystem.entity.entities.CarInsurance;

@Mapper(componentModel = "spring")
public interface CarInsuranceMapper {

    @Mapping(source = "person.country", target = "person.adres.country")
    @Mapping(source = "person.city", target = "person.adres.city")
    @Mapping(source = "person.zipCode", target = "person.adres.zipCode")
    @Mapping(source = "person.street", target = "person.adres.street")
    CarInsurance mapToCarInsurance(CarInsuranceRequest carInsuranceRequest);
}

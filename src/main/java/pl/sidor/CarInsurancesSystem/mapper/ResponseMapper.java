package pl.sidor.CarInsurancesSystem.mapper;

import generated_class.model.CarInsuranceRequest;
import generated_class.model.CarInsuranceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ResponseMapper {

    @Mapping(source = "person.name", target = "name")
    @Mapping(source = "person.lastName", target = "lastName")
    CarInsuranceResponse mapToCarInsuranceResponse(CarInsuranceRequest carInsuranceRequest);
}

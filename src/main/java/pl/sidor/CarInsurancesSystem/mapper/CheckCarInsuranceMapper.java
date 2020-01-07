package pl.sidor.CarInsurancesSystem.mapper;

import generated_class.model.CheckCarInsuranceResponse;
import org.mapstruct.Mapper;
import pl.sidor.CarInsurancesSystem.entity.entities.CarInsurance;

@Mapper
public interface CheckCarInsuranceMapper {

    CheckCarInsuranceResponse mapToResponse(CarInsurance carInsurance);
}

package pl.sidor.CarInsurancesSystem.mapper;

import generated_class.model.PaymentCarInsuranceResponse;
import org.mapstruct.Mapper;
import pl.sidor.CarInsurancesSystem.entity.entities.Car;

@Mapper
public interface PaymentCarInsuranceResponseMapper {

    PaymentCarInsuranceResponse mapTo(Car car);
}

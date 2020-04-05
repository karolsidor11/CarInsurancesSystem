package pl.sidor.CarInsurancesSystem.mapper;

import generated_class.model.PaymentCarInsuranceResponse;
import org.mapstruct.Mapper;
import pl.sidor.CarInsurancesSystem.entity.entities.PaymentCarInsurance;

@Mapper
public interface PaymentCarInsuranceMapper {

    PaymentCarInsurance mapToEntity(PaymentCarInsuranceResponse paymentCarInsuranceResponse);
}

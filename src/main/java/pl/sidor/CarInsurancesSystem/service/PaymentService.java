package pl.sidor.CarInsurancesSystem.service;

import generated_class.model.PaymentCarInsuranceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.sidor.CarInsurancesSystem.entity.entities.PaymentCarInsurance;
import pl.sidor.CarInsurancesSystem.mapper.PaymentCarInsuranceMapper;
import pl.sidor.CarInsurancesSystem.mapper.PaymentCarInsuranceMapperImpl;
import pl.sidor.CarInsurancesSystem.repository.PaymentCarInsuranceRepository;

@Component
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentCarInsuranceRepository paymentCarInsuranceRepository;

    public void savePaymentCarInsurance(PaymentCarInsuranceResponse payments) {
        PaymentCarInsuranceMapper mapper = new PaymentCarInsuranceMapperImpl();
        PaymentCarInsurance paymentCarInsurance = mapper.mapToEntity(payments);
        paymentCarInsuranceRepository.save(paymentCarInsurance);
    }
}

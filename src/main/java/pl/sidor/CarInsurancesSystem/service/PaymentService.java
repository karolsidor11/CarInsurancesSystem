package pl.sidor.CarInsurancesSystem.service;

import generated_class.model.PaymentCarInsuranceRequest;
import generated_class.model.PaymentCarInsuranceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sidor.CarInsurancesSystem.entity.entities.Car;
import pl.sidor.CarInsurancesSystem.entity.entities.PaymentCarInsurance;
import pl.sidor.CarInsurancesSystem.mapper.PaymentCarInsuranceMapper;
import pl.sidor.CarInsurancesSystem.mapper.PaymentCarInsuranceMapperImpl;
import pl.sidor.CarInsurancesSystem.repository.PaymentCarInsuranceRepository;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentCarInsuranceRepository paymentCarInsuranceRepository;
    private final MapperService mapperService;

    public void savePaymentCarInsurance(PaymentCarInsuranceResponse payments) {
        PaymentCarInsuranceMapper mapper = new PaymentCarInsuranceMapperImpl();
        PaymentCarInsurance paymentCarInsurance = mapper.mapToEntity(payments);
        paymentCarInsuranceRepository.save(paymentCarInsurance);
    }

    public PaymentCarInsuranceResponse prepareResponse(PaymentCarInsuranceRequest request, Car car) {
        PaymentCarInsuranceResponse paymentCarInsuranceResponse = new PaymentCarInsuranceResponse();
        PaymentCarInsuranceResponse payments = mapperService.mapCarToResponse(car);
        paymentCarInsuranceResponse.setPolicyNumber(request.getPolicyNumber());
        paymentCarInsuranceResponse.setName(request.getName());
        paymentCarInsuranceResponse.setLastName(request.getLastName());
        paymentCarInsuranceResponse.setMark(payments.getMark());
        paymentCarInsuranceResponse.setModel(payments.getModel());
        paymentCarInsuranceResponse.setRegistryNumber(payments.getRegistryNumber());

        return paymentCarInsuranceResponse;
    }
}

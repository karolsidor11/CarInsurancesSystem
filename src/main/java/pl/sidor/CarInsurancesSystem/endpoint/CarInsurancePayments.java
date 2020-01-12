package pl.sidor.CarInsurancesSystem.endpoint;

import generated_class.model.PaymentCarInsuranceRequest;
import generated_class.model.PaymentCarInsuranceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import pl.sidor.CarInsurancesSystem.entity.entities.Car;
import pl.sidor.CarInsurancesSystem.entity.entities.CarInsurance;
import pl.sidor.CarInsurancesSystem.entity.entities.PaymentCarInsurance;
import pl.sidor.CarInsurancesSystem.exception.ExceptionFactory;
import pl.sidor.CarInsurancesSystem.exception.InsuranceException;
import pl.sidor.CarInsurancesSystem.mapper.PaymentCarInsuranceMapper;
import pl.sidor.CarInsurancesSystem.mapper.PaymentCarInsuranceResponseMapper;
import pl.sidor.CarInsurancesSystem.mapper.PaymentCarInsuranceResponseMapperImpl;
import pl.sidor.CarInsurancesSystem.mapper.PaymentCarInsuranceMapperImpl;
import pl.sidor.CarInsurancesSystem.repository.CarInsuranceRepository;
import pl.sidor.CarInsurancesSystem.repository.PaymentCarInsuranceRepository;

import java.util.Optional;

@Endpoint
public class CarInsurancePayments {

    private static final String NAMESPACE_URI = "http://www.generated_class/model";

    private final PaymentCarInsuranceRepository paymentCarInsuranceRepository;
    private final CarInsuranceRepository carInsuranceRepository;

    @Autowired
    public CarInsurancePayments(PaymentCarInsuranceRepository paymentCarInsuranceRepository, CarInsuranceRepository carInsuranceRepository) {
        this.paymentCarInsuranceRepository = paymentCarInsuranceRepository;
        this.carInsuranceRepository = carInsuranceRepository;
    }


    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "paymentCarInsuranceRequest")
    public PaymentCarInsuranceResponse payments(@RequestPayload PaymentCarInsuranceRequest paymentRequest) throws InsuranceException {
        PaymentCarInsuranceResponse paymentCarInsuranceResponse = new PaymentCarInsuranceResponse();
        Car car = tryFindCarByPolicyNumber(paymentRequest);
        PaymentCarInsuranceResponse paymentCarInsuranceResponse1 = mapCarToResponse(car);
        paymentCarInsuranceResponse.setPolicyNumber(paymentRequest.getPolicyNumber());
        paymentCarInsuranceResponse.setName(paymentRequest.getName());
        paymentCarInsuranceResponse.setLastName(paymentRequest.getLastName());
        paymentCarInsuranceResponse.setMark(paymentCarInsuranceResponse1.getMark());
        paymentCarInsuranceResponse.setModel(paymentCarInsuranceResponse1.getModel());
        paymentCarInsuranceResponse.setRegistryNumber(paymentCarInsuranceResponse1.getRegistryNumber());
        savePaymentCarInsurance(paymentCarInsuranceResponse);

        return paymentCarInsuranceResponse;
    }

    private Car tryFindCarByPolicyNumber(PaymentCarInsuranceRequest paymentCarInsuranceRequest) throws InsuranceException {
        Optional<CarInsurance> byPolicyNumber = carInsuranceRepository.findByPolicyNumber(paymentCarInsuranceRequest.getPolicyNumber());
        return byPolicyNumber.map(CarInsurance::getCar).orElseThrow(ExceptionFactory::brakUbezpieczeniaSamochodu);
    }

    private PaymentCarInsuranceResponse mapCarToResponse(Car car) {
        PaymentCarInsuranceResponseMapper carInsuranceMapper = new PaymentCarInsuranceResponseMapperImpl();
        return carInsuranceMapper.mapTo(car);
    }

    private void savePaymentCarInsurance(PaymentCarInsuranceResponse payments) {
        PaymentCarInsuranceMapper mapper = new PaymentCarInsuranceMapperImpl();
        PaymentCarInsurance paymentCarInsurance = mapper.mapToEntity(payments);
        paymentCarInsuranceRepository.save(paymentCarInsurance);
    }
}

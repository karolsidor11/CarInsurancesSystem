package pl.sidor.CarInsurancesSystem.service;

import generated_class.model.PaymentCarInsuranceRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import pl.sidor.CarInsurancesSystem.entity.entities.Car;
import pl.sidor.CarInsurancesSystem.entity.entities.CarInsurance;
import pl.sidor.CarInsurancesSystem.exception.ExceptionFactory;
import pl.sidor.CarInsurancesSystem.repository.insurance.CarInsuranceRepository;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarInsuranceRepository carInsuranceRepository;

    @SneakyThrows
    public Car tryFindCarByPolicyNumber(PaymentCarInsuranceRequest paymentCarInsuranceRequest) {
        return carInsuranceRepository.findByPolicyNumber(paymentCarInsuranceRequest.getPolicyNumber())
                .map(CarInsurance::getCar)
                .orElseThrow(ExceptionFactory::brakUbezpieczeniaSamochodu);
    }
}

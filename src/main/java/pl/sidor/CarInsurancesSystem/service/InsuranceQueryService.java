package pl.sidor.CarInsurancesSystem.service;

import generated_class.model.CheckCarInsuranceRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import pl.sidor.CarInsurancesSystem.entity.entities.CarInsurance;
import pl.sidor.CarInsurancesSystem.exception.ExceptionFactory;
import pl.sidor.CarInsurancesSystem.repository.insurance.CarInsuranceRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InsuranceQueryService {

    private final CarInsuranceRepository carInsuranceRepository;

    public List<CarInsurance> findByPersonData(String name, String lastName) {
        return carInsuranceRepository.findByPersonNameAndPersonLastName(name, lastName);
    }

    @SneakyThrows
    public CarInsurance findInsuranceByCarRegistryNumber(CheckCarInsuranceRequest checkCarInsuranceRequest) {
        return carInsuranceRepository.findByCarRegistryNumber(checkCarInsuranceRequest.getRegistryNumber()).stream()
                .findFirst()
                .orElseThrow(ExceptionFactory.brakUbezpieczeniaSamochodu());
    }
}

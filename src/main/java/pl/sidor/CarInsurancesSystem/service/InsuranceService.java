package pl.sidor.CarInsurancesSystem.service;

import generated_class.model.CarInsuranceRequest;
import generated_class.model.CheckCarInsuranceRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import pl.sidor.CarInsurancesSystem.entity.entities.CarInsurance;
import pl.sidor.CarInsurancesSystem.exception.ExceptionFactory;
import pl.sidor.CarInsurancesSystem.mapper.CarInsuranceMapper;
import pl.sidor.CarInsurancesSystem.mapper.CarInsuranceMapperImpl;
import pl.sidor.CarInsurancesSystem.repository.insurance.CarInsuranceRepository;
import pl.sidor.CarInsurancesSystem.utils.PolicyNumberGenerator;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InsuranceService {

    private final CarInsuranceRepository carInsuranceRepository;
    private final PolicyNumberGenerator policyNumberGenerator;

    public List<CarInsurance> findByPersonData(String name, String lastName) {
        return carInsuranceRepository.findByPersonNameAndPersonLastName(name, lastName);
    }

    public CarInsurance mapAndSaveInsurance(CarInsuranceRequest carInsuranceRequest) {
        CarInsuranceMapper carInsuranceMapper = new CarInsuranceMapperImpl();
        CarInsurance carInsuranceEndpoint1 = carInsuranceMapper.mapToCarInsurance(carInsuranceRequest);
        carInsuranceEndpoint1.setPolicyNumber(generatePolicyNumber());
        return carInsuranceRepository.save(carInsuranceEndpoint1);
    }

    @SneakyThrows
    public CarInsurance findInsuranceByCarRegistryNumber(CheckCarInsuranceRequest checkCarInsuranceRequest) {
        return carInsuranceRepository.findByCarRegistryNumber(checkCarInsuranceRequest.getRegistryNumber()).stream()
                .findFirst()
                .orElseThrow(ExceptionFactory.brakUbezpieczeniaSamochodu());
    }

    private String generatePolicyNumber() {
        return policyNumberGenerator.generatePolicyNumber();
    }
}

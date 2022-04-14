package pl.sidor.CarInsurancesSystem.service;

import generated_class.model.CarInsuranceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sidor.CarInsurancesSystem.entity.entities.CarInsurance;
import pl.sidor.CarInsurancesSystem.mapper.CarInsuranceMapper;
import pl.sidor.CarInsurancesSystem.mapper.CarInsuranceMapperImpl;
import pl.sidor.CarInsurancesSystem.repository.CarRepository;
import pl.sidor.CarInsurancesSystem.repository.insurance.CarInsuranceRepository;
import pl.sidor.CarInsurancesSystem.utils.PolicyNumberGenerator;

@Service
@RequiredArgsConstructor
public class InsuranceService {

    private final CarInsuranceRepository carInsuranceRepository;
    private final PolicyNumberGenerator policyNumberGenerator;
    private final CarRepository carRepository;

    @Transactional
    public CarInsurance mapAndSaveInsurance(CarInsuranceRequest carInsuranceRequest) {
        CarInsuranceMapper carInsuranceMapper = new CarInsuranceMapperImpl();
        CarInsurance carInsuranceEndpoint1 = carInsuranceMapper.mapToCarInsurance(carInsuranceRequest);
        carInsuranceEndpoint1.setPolicyNumber(generatePolicyNumber());
        CarInsurance carInsurance = carInsuranceRepository.save(carInsuranceEndpoint1);
        carRepository.findById(carInsurance.getCar().getId()).ifPresent(car -> {
            car.setPerson(carInsurance.getPerson());
            carRepository.save(car);
        });

        return carInsurance;
    }

    private String generatePolicyNumber() {
        return policyNumberGenerator.generatePolicyNumber();
    }
}

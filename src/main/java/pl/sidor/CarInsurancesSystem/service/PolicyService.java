package pl.sidor.CarInsurancesSystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sidor.CarInsurancesSystem.entity.entities.CarInsurance;
import pl.sidor.CarInsurancesSystem.repository.insurance.CarInsuranceRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PolicyService {

    private final CarInsuranceRepository carInsuranceRepository;

    public Optional<CarInsurance> tryFindCarInsurance(String policyNumber) {
        return carInsuranceRepository.findByPolicyNumber(policyNumber);
    }
}

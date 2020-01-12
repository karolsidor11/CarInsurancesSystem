package pl.sidor.CarInsurancesSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sidor.CarInsurancesSystem.entity.entities.CarInsurance;

import java.util.Optional;

@Repository
public interface CarInsuranceRepository extends JpaRepository<CarInsurance, Long> {

    Optional<CarInsurance> findByCarRegistryNumber(String registryNumber);
    Optional<CarInsurance> findByPolicyNumber(String policyNumber);
}

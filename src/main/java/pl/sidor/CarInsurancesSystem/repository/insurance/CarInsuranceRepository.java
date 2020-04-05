package pl.sidor.CarInsurancesSystem.repository.insurance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sidor.CarInsurancesSystem.entity.entities.CarInsurance;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarInsuranceRepository extends JpaRepository<CarInsurance, Long>, CarInsuranceCustom {

    List<CarInsurance> findByCarRegistryNumber(String registryNumber);

    Optional<CarInsurance> findByPolicyNumber(String policyNumber);

    List<CarInsurance> findByPersonNameAndPersonLastName(String name, String lastName);
}

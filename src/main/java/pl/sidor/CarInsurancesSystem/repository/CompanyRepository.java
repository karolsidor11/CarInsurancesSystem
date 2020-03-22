package pl.sidor.CarInsurancesSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sidor.CarInsurancesSystem.entity.entities.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}

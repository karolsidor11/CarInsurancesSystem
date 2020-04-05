package pl.sidor.CarInsurancesSystem.repository.insurance;

import generated_class.model.Person;
import org.springframework.stereotype.Repository;
import pl.sidor.CarInsurancesSystem.entity.entities.Car;
import pl.sidor.CarInsurancesSystem.entity.entities.CarInsurance;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class CarInsuranceCustomImpl implements CarInsuranceCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CarInsurance> findByData(String name, String lastName, String number) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CarInsurance> query = criteriaBuilder.createQuery(CarInsurance.class);
        Root<CarInsurance> root = query.from(CarInsurance.class);

        Join<CarInsurance, Car> carJoin = root.join("car", JoinType.INNER);
        Join<CarInsurance, Person> personJoin = root.join("person", JoinType.INNER);

        Predicate registryNumberPredicate = criteriaBuilder.equal(carJoin.get("registryNumber"), number);
        Predicate namePredicate = criteriaBuilder.equal(personJoin.get("name"), name);
        Predicate lastNamePredicate = criteriaBuilder.equal(personJoin.get("lastName"), lastName);

        query.where(registryNumberPredicate, namePredicate, lastNamePredicate);

        return entityManager.createQuery(query).getResultList();
    }
}

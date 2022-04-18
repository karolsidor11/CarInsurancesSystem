package pl.sidor.CarInsurancesSystem.repository.insurance;

import generated_class.model.Person;
import org.springframework.stereotype.Repository;
import pl.sidor.CarInsurancesSystem.entity.entities.Car;
import pl.sidor.CarInsurancesSystem.entity.entities.CarInsurance;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.List;

import static pl.sidor.CarInsurancesSystem.utils.GeneralUtils.CAR;
import static pl.sidor.CarInsurancesSystem.utils.GeneralUtils.CAR_REGISTRY_NUMBER;
import static pl.sidor.CarInsurancesSystem.utils.GeneralUtils.PERSON;
import static pl.sidor.CarInsurancesSystem.utils.GeneralUtils.PERSON_NAME;
import static pl.sidor.CarInsurancesSystem.utils.GeneralUtils.PERSON_LASTNAME;

@Repository
public class CarInsuranceCustomImpl implements CarInsuranceCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CarInsurance> findByData(String name, String lastName, String number) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CarInsurance> query = criteriaBuilder.createQuery(CarInsurance.class);
        Root<CarInsurance> root = query.from(CarInsurance.class);

        Join<CarInsurance, Car> carJoin = root.join(CAR, JoinType.INNER);
        Join<CarInsurance, Person> personJoin = root.join(PERSON, JoinType.INNER);

        Predicate registryNumberPredicate = criteriaBuilder.equal(carJoin.get(CAR_REGISTRY_NUMBER), number);
        Predicate namePredicate = criteriaBuilder.equal(personJoin.get(PERSON_NAME), name);
        Predicate lastNamePredicate = criteriaBuilder.equal(personJoin.get(PERSON_LASTNAME), lastName);

        query.where(registryNumberPredicate, namePredicate, lastNamePredicate);

        return entityManager.createQuery(query).getResultList();
    }
}

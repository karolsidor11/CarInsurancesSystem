package pl.sidor.CarInsurancesSystem.validations.predicate;

import generated_class.model.Person;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
public class PersonPredicate implements Predicate<Person> {

    @Override
    public boolean test(Person person) {
        return person.getName().isEmpty() || person.getLastName().isEmpty();
    }
}

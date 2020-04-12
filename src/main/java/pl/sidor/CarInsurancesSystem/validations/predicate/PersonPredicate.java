package pl.sidor.CarInsurancesSystem.validations.predicate;

import generated_class.model.Person;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.function.Predicate;

@Component
public class PersonPredicate implements Predicate<Person> {

    @Override
    public boolean test(Person person) {
        if (Objects.isNull(person.getName()) || Objects.isNull(person.getLastName())) {
            return true;
        }
        return person.getName().isEmpty() || person.getLastName().isEmpty();
    }
}

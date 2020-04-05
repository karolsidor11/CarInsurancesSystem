package pl.sidor.CarInsurancesSystem.validations.factory;

import generated_class.model.CarInsuranceRequest;
import generated_class.model.Person;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import pl.sidor.CarInsurancesSystem.exception.InsuranceException;
import pl.sidor.CarInsurancesSystem.exception.MessageException;
import pl.sidor.CarInsurancesSystem.validations.predicate.CarPredicate;
import pl.sidor.CarInsurancesSystem.validations.predicate.DatesPredicate;
import pl.sidor.CarInsurancesSystem.validations.predicate.PersonPredicate;
import pl.sidor.CarInsurancesSystem.validations.predicate.PeselPredicate;

import java.math.BigInteger;

@RequiredArgsConstructor
public abstract class CarConditionFactory<T> {

    private final PeselPredicate peselPredicate;
    private final PersonPredicate personPredicate;
    private final DatesPredicate datesPredicate;
    private final CarPredicate carPredicate;

    protected abstract void setConditions(T request) throws InsuranceException;

    protected abstract void throwException(MessageException exception) throws InsuranceException;

    protected boolean peselValidate(BigInteger pesel) {
        return peselPredicate.test(pesel);
    }

    protected boolean personValidate(Person person) {
        return personPredicate.test(person);
    }

    protected boolean dateValidator(CarInsuranceRequest request) {
        return datesPredicate.test(request);
    }

    protected boolean carValidator(CarInsuranceRequest request) {
        return carPredicate.test(request);
    }

    public void validate(T request) throws InsuranceException {
        setConditions(request);
    }

    @SneakyThrows
    protected void addCondition(MessageException message, boolean condition) {
        if (condition) {
            throwException(message);
        }
    }
}

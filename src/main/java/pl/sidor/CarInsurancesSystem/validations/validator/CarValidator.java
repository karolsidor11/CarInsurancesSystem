package pl.sidor.CarInsurancesSystem.validations.validator;

import generated_class.model.CarInsuranceRequest;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import pl.sidor.CarInsurancesSystem.exception.ExceptionFactory;
import pl.sidor.CarInsurancesSystem.exception.MessageException;
import pl.sidor.CarInsurancesSystem.validations.factory.CarConditionFactory;
import pl.sidor.CarInsurancesSystem.validations.predicate.CarPredicate;
import pl.sidor.CarInsurancesSystem.validations.predicate.DatesPredicate;
import pl.sidor.CarInsurancesSystem.validations.predicate.PersonPredicate;
import pl.sidor.CarInsurancesSystem.validations.predicate.PeselPredicate;

import static pl.sidor.CarInsurancesSystem.exception.MessageException.*;

@Component
public class CarValidator extends CarConditionFactory<CarInsuranceRequest> {

    public CarValidator(PeselPredicate peselPredicate, PersonPredicate personPredicate, DatesPredicate datesPredicate, CarPredicate carPredicate) {
        super(peselPredicate, personPredicate, datesPredicate, carPredicate);
    }

    @Override
    protected void setConditions(CarInsuranceRequest request) {
        addCondition(NIEPOPRAWNY_PESEL, peselValidate(request.getPerson().getPesel()));
        addCondition(NIEPRAWIDLOWE_DANE_OSOBOWE, personValidate(request.getPerson()));
        addCondition(BLEDNE_DATY_UBEZPIECZENIA, dateValidator(request));
        addCondition(NIEPOPRAWNE_DANE_POJAZDU, carValidator(request));
    }

    @Override
    @SneakyThrows()
    protected void throwException(MessageException exception) {
        throw ExceptionFactory.create(exception);
    }
}

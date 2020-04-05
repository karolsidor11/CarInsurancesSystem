package pl.sidor.CarInsurancesSystem.validations.validator;

import generated_class.model.PaymentCarInsuranceRequest;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import pl.sidor.CarInsurancesSystem.exception.ExceptionFactory;
import pl.sidor.CarInsurancesSystem.exception.MessageException;
import pl.sidor.CarInsurancesSystem.validations.factory.PaymentConditionFactory;
import pl.sidor.CarInsurancesSystem.validations.predicate.PersonDataPredicate;
import pl.sidor.CarInsurancesSystem.validations.predicate.PolicyNumberPredicate;

import static pl.sidor.CarInsurancesSystem.exception.MessageException.BLEDNE_DATY_UBEZPIECZENIA;
import static pl.sidor.CarInsurancesSystem.exception.MessageException.NIE_ZNALEZIONO_UBEZPIECZENIA;

@Component
public class PaymentValidator extends PaymentConditionFactory<PaymentCarInsuranceRequest> {

    public PaymentValidator(PolicyNumberPredicate policyNumberPredicate, PersonDataPredicate personDataPredicate) {
        super(policyNumberPredicate, personDataPredicate);
    }

    @Override
    public void setCondition(PaymentCarInsuranceRequest request) {
        addCondition(BLEDNE_DATY_UBEZPIECZENIA, checkPolicyNumber(request));
        addCondition(NIE_ZNALEZIONO_UBEZPIECZENIA, checkPersonData(request));
    }

    @Override
    @SneakyThrows
    public void throwException(MessageException message) {
        throw ExceptionFactory.create(message);
    }
}

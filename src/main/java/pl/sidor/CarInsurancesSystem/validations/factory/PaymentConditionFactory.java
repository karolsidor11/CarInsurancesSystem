package pl.sidor.CarInsurancesSystem.validations.factory;

import generated_class.model.PaymentCarInsuranceRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import pl.sidor.CarInsurancesSystem.exception.MessageException;
import pl.sidor.CarInsurancesSystem.validations.predicate.PersonDataPredicate;
import pl.sidor.CarInsurancesSystem.validations.predicate.PolicyNumberPredicate;

@Component
@RequiredArgsConstructor
public abstract class PaymentConditionFactory<T> {

    private final PolicyNumberPredicate policyNumberPredicate;
    private final PersonDataPredicate personDataPredicate;

    public abstract void setCondition(T request);

    public abstract void throwException(MessageException message);

    protected boolean checkPolicyNumber(PaymentCarInsuranceRequest request) {
        return policyNumberPredicate.test(request);
    }

    protected boolean checkPersonData(PaymentCarInsuranceRequest request) {
        return personDataPredicate.test(request);
    }

    @SneakyThrows
    public void validate(T request) {
        setCondition(request);
    }

    protected void addCondition(MessageException message, boolean condition) {
        if (condition) {
            throwException(message);
        }
    }
}

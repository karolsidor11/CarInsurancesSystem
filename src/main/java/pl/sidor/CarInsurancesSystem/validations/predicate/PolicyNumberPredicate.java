package pl.sidor.CarInsurancesSystem.validations.predicate;

import generated_class.model.PaymentCarInsuranceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.sidor.CarInsurancesSystem.service.PolicyService;

import java.util.function.Predicate;

@Component
@RequiredArgsConstructor
public class PolicyNumberPredicate implements Predicate<PaymentCarInsuranceRequest> {

    private final PolicyService policyService;

    @Override
    public boolean test(PaymentCarInsuranceRequest request) {
        return policyService.tryFindCarInsurance(request.getPolicyNumber()).isPresent();
    }
}

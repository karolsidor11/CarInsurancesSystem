package pl.sidor.CarInsurancesSystem.validations.predicate;

import generated_class.model.PaymentCarInsuranceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sidor.CarInsurancesSystem.service.InsuranceQueryService;

import java.util.Objects;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
public class PersonDataPredicate implements Predicate<PaymentCarInsuranceRequest> {

    private final InsuranceQueryService insuranceService;

    @Override
    public boolean test(PaymentCarInsuranceRequest request) {
        if (Objects.isNull(request.getName()) || Objects.isNull(request.getLastName())) {
            return false;
        }
        return insuranceService.findByPersonData(request.getName(), request.getLastName()).stream().anyMatch(Objects::nonNull);
    }
}

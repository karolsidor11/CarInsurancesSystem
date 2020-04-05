package pl.sidor.CarInsurancesSystem.validations.predicate;


import generated_class.model.CarInsuranceRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.function.Predicate;

import static java.util.Objects.isNull;

@Component
public class DatesPredicate implements Predicate<CarInsuranceRequest> {

    @Override
    public boolean test(CarInsuranceRequest request) {
        return isNull(request.getDateFrom()) || isNull(request.getDateTo()) || dateFromIsBeforeDateTo(request);
    }

    private boolean dateFromIsBeforeDateTo(CarInsuranceRequest carInsuranceRequest) {
        LocalDateTime dateFrom = carInsuranceRequest.getDateFrom().toGregorianCalendar().toZonedDateTime().toLocalDateTime();
        LocalDateTime dateTo = carInsuranceRequest.getDateTo().toGregorianCalendar().toZonedDateTime().toLocalDateTime();
        return dateFrom.isAfter(dateTo);
    }
}

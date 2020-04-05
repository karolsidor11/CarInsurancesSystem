package pl.sidor.CarInsurancesSystem.validations.predicate;

import generated_class.model.Car;
import generated_class.model.CarInsuranceRequest;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

import static java.util.Objects.isNull;

@Component
public class CarPredicate implements Predicate<CarInsuranceRequest> {

    @Override
    public boolean test(CarInsuranceRequest carInsuranceRequest) {
        Car car = carInsuranceRequest.getCar();
        return isNull(car.getMark()) || isNull(car.getModel()) || isNull(car.getRegistryNumber());
    }
}

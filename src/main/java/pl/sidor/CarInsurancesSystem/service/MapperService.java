package pl.sidor.CarInsurancesSystem.service;

import generated_class.model.CheckCarInsuranceResponse;
import generated_class.model.PaymentCarInsuranceResponse;
import org.springframework.stereotype.Component;
import pl.sidor.CarInsurancesSystem.entity.entities.Car;
import pl.sidor.CarInsurancesSystem.entity.entities.CarInsurance;
import pl.sidor.CarInsurancesSystem.mapper.CheckCarInsuranceMapper;
import pl.sidor.CarInsurancesSystem.mapper.CheckCarInsuranceMapperImpl;
import pl.sidor.CarInsurancesSystem.mapper.PaymentCarInsuranceResponseMapper;
import pl.sidor.CarInsurancesSystem.mapper.PaymentCarInsuranceResponseMapperImpl;

@Component
public class MapperService {

    public CheckCarInsuranceResponse mapCarInsurance(CarInsurance carInsurance) {
        CheckCarInsuranceMapper carInsuranceMapper = new CheckCarInsuranceMapperImpl();
        return carInsuranceMapper.mapToResponse(carInsurance);
    }

    public PaymentCarInsuranceResponse mapCarToResponse(Car car) {
        PaymentCarInsuranceResponseMapper carInsuranceMapper = new PaymentCarInsuranceResponseMapperImpl();
        return carInsuranceMapper.mapTo(car);
    }
}

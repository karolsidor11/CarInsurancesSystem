package pl.sidor.CarInsurancesSystem.repository.insurance;

import pl.sidor.CarInsurancesSystem.entity.entities.CarInsurance;

import java.util.List;

public  interface CarInsuranceCustom {

    List<CarInsurance> findByData(String name, String lastName, String number);
}

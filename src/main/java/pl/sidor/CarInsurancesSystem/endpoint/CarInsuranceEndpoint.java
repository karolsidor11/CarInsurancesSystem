package pl.sidor.CarInsurancesSystem.endpoint;

import generated_class.model.CarInsuranceRequest;
import generated_class.model.CarInsuranceRespone;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import pl.sidor.CarInsurancesSystem.entity.entities.CarInsurance;
import pl.sidor.CarInsurancesSystem.repository.CarInsuranceRepository;

@Endpoint
@AllArgsConstructor
public class CarInsuranceEndpoint {

    private static final String NAMESPACE_URI = "http://www.generated_class/model";

    @Autowired
    private CarInsuranceRepository carInsuranceRepository;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "carInsuranceRequest")
    @ResponsePayload
    public CarInsuranceRespone carInsuranceRespone(@RequestPayload CarInsuranceRequest carInsuranceRequest) {
        CarInsuranceRespone carInsuranceRespone = new CarInsuranceRespone();
        carInsuranceRespone.setCar(carInsuranceRequest.getCar());
        return carInsuranceRespone;
    }
}

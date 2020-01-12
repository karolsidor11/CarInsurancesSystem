package pl.sidor.CarInsurancesSystem.endpoint;

import generated_class.model.CheckCarInsuranceRequest;
import generated_class.model.CheckCarInsuranceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import pl.sidor.CarInsurancesSystem.entity.entities.CarInsurance;
import pl.sidor.CarInsurancesSystem.exception.ExceptionFactory;
import pl.sidor.CarInsurancesSystem.mapper.CheckCarInsuranceMapper;
import pl.sidor.CarInsurancesSystem.mapper.CheckCarInsuranceMapperImpl;
import pl.sidor.CarInsurancesSystem.repository.CarInsuranceRepository;

@Endpoint
public class CarInsuranceVerificationEndpoint {

    private static final String NAMESPACE_URI = "http://www.generated_class/model";

    private final CarInsuranceRepository carInsuranceRepository;

    @Autowired
    public CarInsuranceVerificationEndpoint(CarInsuranceRepository carInsuranceRepository) {
        this.carInsuranceRepository = carInsuranceRepository;
    }

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "checkCarInsuranceRequest")
    public CheckCarInsuranceResponse checkCarInsurance(@RequestPayload CheckCarInsuranceRequest checkCarInsuranceRequest) throws Throwable {
        CarInsurance insuranceByCarRegistryNumber = findInsuranceByCarRegistryNumber(checkCarInsuranceRequest);
        return mapCarInsurance(insuranceByCarRegistryNumber);
    }

    private CarInsurance findInsuranceByCarRegistryNumber(CheckCarInsuranceRequest checkCarInsuranceRequest) throws Throwable {
        return carInsuranceRepository.findByCarRegistryNumber(checkCarInsuranceRequest.getRegistryNumber())
                .orElseThrow(ExceptionFactory.brakUbezpieczeniaSamochodu());
    }

    private CheckCarInsuranceResponse mapCarInsurance(CarInsurance carInsurance) {
        CheckCarInsuranceMapper carInsuranceMapper = new CheckCarInsuranceMapperImpl();
        return carInsuranceMapper.mapToResponse(carInsurance);
    }
}

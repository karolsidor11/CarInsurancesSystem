package pl.sidor.CarInsurancesSystem.endpoint;

import generated_class.model.CarInsuranceRequest;
import generated_class.model.CarInsuranceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import pl.sidor.CarInsurancesSystem.entity.entities.CarInsurance;
import pl.sidor.CarInsurancesSystem.mapper.CarInsuranceMapper;
import pl.sidor.CarInsurancesSystem.mapper.CarInsuranceMapperImpl;
import pl.sidor.CarInsurancesSystem.mapper.ResponseMapper;
import pl.sidor.CarInsurancesSystem.mapper.ResponseMapperImpl;
import pl.sidor.CarInsurancesSystem.repository.CarInsuranceRepository;
import pl.sidor.CarInsurancesSystem.utils.PolicyNumberGenerator;

@Endpoint
public class CarInsuranceEndpoint {

    private static final String NAMESPACE_URI = "http://www.generated_class/model";

    private final CarInsuranceRepository carInsuranceRepository;
    private final PolicyNumberGenerator policyNumberGenerator;

    @Autowired
    public CarInsuranceEndpoint(CarInsuranceRepository carInsuranceRepository, PolicyNumberGenerator policyNumberGenerator) {
        this.carInsuranceRepository = carInsuranceRepository;
        this.policyNumberGenerator = policyNumberGenerator;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "carInsuranceRequest")
    @ResponsePayload
    public CarInsuranceResponse carInsuranceResponse(@RequestPayload CarInsuranceRequest carInsuranceRequest) {
        ResponseMapper responseMapper = new ResponseMapperImpl();
        CarInsuranceResponse carInsuranceResponse = responseMapper.mapToCarInsuranceResponse(carInsuranceRequest);
        mapAndSaveInsurance(carInsuranceRequest);

        return carInsuranceResponse;
    }

    private void mapAndSaveInsurance(CarInsuranceRequest carInsuranceRequest) {
        CarInsuranceMapper carInsuranceMapper = new CarInsuranceMapperImpl();
        CarInsurance carInsurance1 = carInsuranceMapper.mapToCarInsurance(carInsuranceRequest);
        carInsurance1.setPolicyNumber(generatePolicyNumber());

        carInsuranceRepository.saveAndFlush(carInsurance1);
    }

    private String generatePolicyNumber() {
        return policyNumberGenerator.generatePolicyNumber();
    }
}


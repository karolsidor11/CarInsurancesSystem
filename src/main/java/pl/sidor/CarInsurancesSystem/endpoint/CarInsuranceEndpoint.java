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

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "carInsuranceRequest")
    public CarInsuranceResponse carInsuranceResponse(@RequestPayload CarInsuranceRequest carInsuranceRequest) {
        ResponseMapper responseMapper = new ResponseMapperImpl();
        CarInsuranceResponse carInsuranceResponse = responseMapper.mapToCarInsuranceResponse(carInsuranceRequest);
        CarInsurance carInsurance = mapAndSaveInsurance(carInsuranceRequest);
        carInsuranceResponse.setPolicyNumber(carInsurance.getPolicyNumber());

        return carInsuranceResponse;
    }

    private CarInsurance mapAndSaveInsurance(CarInsuranceRequest carInsuranceRequest) {
        CarInsuranceMapper carInsuranceMapper = new CarInsuranceMapperImpl();
        CarInsurance carInsuranceEndpoint1 = carInsuranceMapper.mapToCarInsurance(carInsuranceRequest);
        carInsuranceEndpoint1.setPolicyNumber(generatePolicyNumber());

        return carInsuranceRepository.save(carInsuranceEndpoint1);
    }

    private String generatePolicyNumber() {
        return policyNumberGenerator.generatePolicyNumber();
    }
}


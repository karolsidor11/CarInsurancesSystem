package pl.sidor.CarInsurancesSystem.endpoint;

import generated_class.model.CarInsuranceRequest;
import generated_class.model.CarInsuranceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import pl.sidor.CarInsurancesSystem.entity.entities.CarInsurance;
import pl.sidor.CarInsurancesSystem.mapper.ResponseMapper;
import pl.sidor.CarInsurancesSystem.mapper.ResponseMapperImpl;
import pl.sidor.CarInsurancesSystem.service.InsuranceService;
import pl.sidor.CarInsurancesSystem.validations.validator.CarValidator;

import static pl.sidor.CarInsurancesSystem.utils.GeneralUtils.NAMESPACE_URI;

@Endpoint
@RequiredArgsConstructor
public class CarInsuranceEndpoint {

    private final InsuranceService insuranceService;
    private final CarValidator carValidator;

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "carInsuranceRequest")
    public CarInsuranceResponse carInsuranceResponse(@RequestPayload CarInsuranceRequest carInsuranceRequest) {
        carValidator.validate(carInsuranceRequest);
        ResponseMapper responseMapper = new ResponseMapperImpl();
        CarInsuranceResponse carInsuranceResponse = responseMapper.mapToCarInsuranceResponse(carInsuranceRequest);
        CarInsurance carInsurance = insuranceService.mapAndSaveInsurance(carInsuranceRequest);
        carInsuranceResponse.setPolicyNumber(carInsurance.getPolicyNumber());

        return carInsuranceResponse;
    }
}


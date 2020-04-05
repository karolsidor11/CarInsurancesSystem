package pl.sidor.CarInsurancesSystem.endpoint;

import generated_class.model.CheckCarInsuranceRequest;
import generated_class.model.CheckCarInsuranceResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import pl.sidor.CarInsurancesSystem.entity.entities.CarInsurance;
import pl.sidor.CarInsurancesSystem.service.InsuranceService;
import pl.sidor.CarInsurancesSystem.service.MapperService;

@Endpoint
@RequiredArgsConstructor
public class CarInsuranceVerificationEndpoint {

    private static final String NAMESPACE_URI = "http://www.generated_class/model";
    private final InsuranceService insuranceService;
    private final MapperService mapperService;

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "checkCarInsuranceRequest")
    @SneakyThrows
    public CheckCarInsuranceResponse checkCarInsurance(@RequestPayload CheckCarInsuranceRequest checkCarInsuranceRequest) {
        CarInsurance insuranceByCarRegistryNumber = insuranceService.findInsuranceByCarRegistryNumber(checkCarInsuranceRequest);
        return mapperService.mapCarInsurance(insuranceByCarRegistryNumber);
    }
}

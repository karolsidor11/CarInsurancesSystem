package pl.sidor.CarInsurancesSystem.endpoint;

import generated_class.model.CheckCarInsuranceRequest;
import generated_class.model.CheckCarInsuranceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import pl.sidor.CarInsurancesSystem.entity.entities.CarInsurance;
import pl.sidor.CarInsurancesSystem.service.InsuranceQueryService;
import pl.sidor.CarInsurancesSystem.service.MapperService;

import static pl.sidor.CarInsurancesSystem.utils.GeneralUtils.NAMESPACE_URI;

@Endpoint
@RequiredArgsConstructor
public class CarInsuranceVerificationEndpoint {

    private final InsuranceQueryService insuranceService;
    private final MapperService mapperService;

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "checkCarInsuranceRequest")
    public CheckCarInsuranceResponse checkCarInsurance(@RequestPayload CheckCarInsuranceRequest checkCarInsuranceRequest) {
        CarInsurance insuranceByCarRegistryNumber = insuranceService.findInsuranceByCarRegistryNumber(checkCarInsuranceRequest);
        return mapperService.mapCarInsurance(insuranceByCarRegistryNumber);
    }
}

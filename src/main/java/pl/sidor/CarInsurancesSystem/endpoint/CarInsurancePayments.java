package pl.sidor.CarInsurancesSystem.endpoint;

import generated_class.model.PaymentCarInsuranceRequest;
import generated_class.model.PaymentCarInsuranceResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import pl.sidor.CarInsurancesSystem.entity.entities.Car;
import pl.sidor.CarInsurancesSystem.service.CarService;
import pl.sidor.CarInsurancesSystem.service.MapperService;
import pl.sidor.CarInsurancesSystem.service.PaymentService;
import pl.sidor.CarInsurancesSystem.validations.validator.PaymentValidator;

@Endpoint
@RequiredArgsConstructor
public class CarInsurancePayments {

    private static final String NAMESPACE_URI = "http://www.generated_class/model";

    private final PaymentValidator paymentValidator;
    private final PaymentService paymentService;
    private final MapperService mapperService;
    private final CarService carService;

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "paymentCarInsuranceRequest")
    @SneakyThrows
    public PaymentCarInsuranceResponse payments(@RequestPayload PaymentCarInsuranceRequest paymentRequest) {
        paymentValidator.validate(paymentRequest);
        PaymentCarInsuranceResponse paymentCarInsuranceResponse = new PaymentCarInsuranceResponse();
        Car car = carService.tryFindCarByPolicyNumber(paymentRequest);
        PaymentCarInsuranceResponse paymentCarInsuranceResponse1 = mapperService.mapCarToResponse(car);
        paymentCarInsuranceResponse.setPolicyNumber(paymentRequest.getPolicyNumber());
        paymentCarInsuranceResponse.setName(paymentRequest.getName());
        paymentCarInsuranceResponse.setLastName(paymentRequest.getLastName());
        paymentCarInsuranceResponse.setMark(paymentCarInsuranceResponse1.getMark());
        paymentCarInsuranceResponse.setModel(paymentCarInsuranceResponse1.getModel());
        paymentCarInsuranceResponse.setRegistryNumber(paymentCarInsuranceResponse1.getRegistryNumber());
        paymentService.savePaymentCarInsurance(paymentCarInsuranceResponse);

        return paymentCarInsuranceResponse;
    }
}

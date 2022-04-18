package pl.sidor.CarInsurancesSystem.endpoint;

import generated_class.model.PaymentCarInsuranceRequest;
import generated_class.model.PaymentCarInsuranceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import pl.sidor.CarInsurancesSystem.entity.entities.Car;
import pl.sidor.CarInsurancesSystem.service.CarService;
import pl.sidor.CarInsurancesSystem.service.PaymentService;
import pl.sidor.CarInsurancesSystem.validations.validator.PaymentValidator;

import static pl.sidor.CarInsurancesSystem.utils.GeneralUtils.NAMESPACE_URI;

@Endpoint
@RequiredArgsConstructor
public class CarInsurancePayments {

    private final PaymentValidator paymentValidator;
    private final PaymentService paymentService;
    private final CarService carService;

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "paymentCarInsuranceRequest")
    public PaymentCarInsuranceResponse payments(@RequestPayload PaymentCarInsuranceRequest paymentRequest) {
        paymentValidator.validate(paymentRequest);
        Car car = carService.tryFindCarByPolicyNumber(paymentRequest);
        PaymentCarInsuranceResponse response = paymentService.prepareResponse(paymentRequest, car);
        paymentService.savePaymentCarInsurance(response);
        return response;
    }
}

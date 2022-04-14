package groovy.pl.sidor.CarInsurancesSystem.service

import generated_class.model.PaymentCarInsuranceRequest
import generated_class.model.PaymentCarInsuranceResponse
import pl.sidor.CarInsurancesSystem.entity.entities.Car
import pl.sidor.CarInsurancesSystem.entity.entities.PaymentCarInsurance
import pl.sidor.CarInsurancesSystem.repository.PaymentCarInsuranceRepository
import pl.sidor.CarInsurancesSystem.service.MapperService
import pl.sidor.CarInsurancesSystem.service.PaymentService
import spock.lang.Specification

class PaymentServiceTest extends Specification {

    PaymentCarInsuranceRepository paymentCarInsuranceRepository = Mock()
    MapperService mapperService = Mock()
    PaymentService paymentService = [paymentCarInsuranceRepository, mapperService]

    def "should save Payment for Insurance"() {
        given:
        PaymentCarInsuranceResponse payments = Stub()
        PaymentCarInsurance paymentCarInsurance = Stub()

        when:
        paymentCarInsuranceRepository.save(_) >> paymentCarInsurance
        paymentService.savePaymentCarInsurance(payments)

        then:
        1 * paymentCarInsuranceRepository.save(_)
    }

    def "should prepare payments response"() {
        given:
        PaymentCarInsuranceRequest request = getRequest()
        Car car = getCar()

        when:
        mapperService.mapCarToResponse(_ as Car) >> []
        def result = paymentService.prepareResponse(request, car)

        then:
        result != null
        noExceptionThrown()
    }

    private static PaymentCarInsuranceRequest getRequest() {
        PaymentCarInsuranceRequest request = new PaymentCarInsuranceRequest()
        request.setName("Jan")
        request.setLastName("Nowak")
        request.setPolicyNumber("12345")
        request
    }

    private Car getCar() {
        Car car = Stub() {
            getMark() >> "Audi"
            getModel() >> "A6"
            getRegistryNumber() >> "LUB997"
        }
        car
    }
}

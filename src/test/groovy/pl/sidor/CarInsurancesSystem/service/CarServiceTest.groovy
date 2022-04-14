package groovy.pl.sidor.CarInsurancesSystem.service

import generated_class.model.PaymentCarInsuranceRequest
import pl.sidor.CarInsurancesSystem.entity.entities.CarInsurance
import pl.sidor.CarInsurancesSystem.exception.InsuranceException
import pl.sidor.CarInsurancesSystem.repository.insurance.CarInsuranceRepository
import pl.sidor.CarInsurancesSystem.service.CarService
import spock.lang.Shared
import spock.lang.Specification

class CarServiceTest extends Specification {

    CarInsuranceRepository carInsuranceRepository = Mock(CarInsuranceRepository.class)
    CarService carService = [carInsuranceRepository]

    @Shared
    String policyNumber = "Policy2020"

    def "should find Car  by PolicyNumber"() {
        given:
        PaymentCarInsuranceRequest request = getRequest(policyNumber)
        CarInsurance carInsurance = Stub()

        when:
        carInsuranceRepository.findByPolicyNumber(_ as String) >> Optional.of(carInsurance)
        def result = carService.tryFindCarByPolicyNumber(request)

        then:
        result != null
    }

    def "should not find Car  by PolicyNumber"() {
        given:
        PaymentCarInsuranceRequest request = getRequest(policyNumber)

        when:
        carInsuranceRepository.findByPolicyNumber(_ as String) >> Optional.empty()
        carService.tryFindCarByPolicyNumber(request)

        then:
        thrown(InsuranceException.class)
    }

    PaymentCarInsuranceRequest getRequest(String policyNumber) {
        PaymentCarInsuranceRequest request = new PaymentCarInsuranceRequest()
        request.setPolicyNumber(policyNumber)
        return request
    }
}

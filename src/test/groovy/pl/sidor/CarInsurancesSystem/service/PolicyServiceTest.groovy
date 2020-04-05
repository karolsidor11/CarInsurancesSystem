package pl.sidor.CarInsurancesSystem.service

import pl.sidor.CarInsurancesSystem.entity.entities.CarInsurance
import pl.sidor.CarInsurancesSystem.repository.insurance.CarInsuranceRepository
import spock.lang.Shared
import spock.lang.Specification

class PolicyServiceTest extends Specification {

    CarInsuranceRepository carInsuranceRepository = Mock(CarInsuranceRepository.class)
    PolicyService policyService = [carInsuranceRepository]

    @Shared
    String policyNumber = "Policy987654"

    def "should find CarInsurance by PolicyNumber"() {
        given:
        CarInsurance carInsurance = Stub()

        when:
        carInsuranceRepository.findByPolicyNumber(policyNumber) >> Optional.of(carInsurance)
        def result = policyService.tryFindCarInsurance(policyNumber)
        then:
        result != null
        result.isPresent()
    }

    def "should return Optional.empty() if PolicyNumber is incorrect"() {
        when:
        carInsuranceRepository.findByPolicyNumber(policyNumber) >> Optional.empty()
        def result = policyService.tryFindCarInsurance(policyNumber)

        then:
        result != null
        !result.isPresent()
    }
}

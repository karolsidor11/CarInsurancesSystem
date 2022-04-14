package groovy.pl.sidor.CarInsurancesSystem.validations.predicate

import generated_class.model.PaymentCarInsuranceRequest
import pl.sidor.CarInsurancesSystem.entity.entities.CarInsurance
import pl.sidor.CarInsurancesSystem.service.PolicyService
import pl.sidor.CarInsurancesSystem.validations.predicate.PolicyNumberPredicate
import spock.lang.Specification
import spock.lang.Unroll

class PolicyNumberPredicateTest extends Specification {

    PolicyService policyService = Mock(PolicyService.class)
    PolicyNumberPredicate policyNumberPredicate = [policyService]

    @Unroll
    def "should validate PolicyNumber where #policyNumber"() {
        given:
        PaymentCarInsuranceRequest request = new PaymentCarInsuranceRequest()
        request.setPolicyNumber(policyNumber)

        when:
        policyService.tryFindCarInsurance(policyNumber) >> carInsurance

        def result = policyNumberPredicate.test(request)

        then:
        result == expectedResult

        where:
        policyNumber | carInsurance                    | expectedResult
        "123466"     | Optional.empty()                | false
        "PL12345"    | Optional.of(new CarInsurance()) | true
    }
}

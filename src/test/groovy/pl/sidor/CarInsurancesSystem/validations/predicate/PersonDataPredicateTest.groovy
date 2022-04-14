package groovy.pl.sidor.CarInsurancesSystem.validations.predicate

import generated_class.model.PaymentCarInsuranceRequest
import pl.sidor.CarInsurancesSystem.entity.entities.CarInsurance
import pl.sidor.CarInsurancesSystem.service.InsuranceQueryService
import pl.sidor.CarInsurancesSystem.validations.predicate.PersonDataPredicate
import spock.lang.Specification
import spock.lang.Unroll

class PersonDataPredicateTest extends Specification {

    InsuranceQueryService insuranceService = Mock()
    PersonDataPredicate personDataPredicate = [insuranceService]

    @Unroll
    def "should validate PersonData"() {
        given:
        PaymentCarInsuranceRequest request = new PaymentCarInsuranceRequest()
        request.setName(name)
        request.setLastName(lastName)

        when:
        insuranceService.findByPersonData(_ as String, _ as String) >> person
        def result = personDataPredicate.test(request)

        then:
        result == expectedResult

        where:
        name    | lastName | person               | expectedResult
        "Jan"   | "Nowak"  | []                   | false
        null    | null     | []                   | false
        "Piotr" | "Nowak"  | [new CarInsurance()] | true
    }
}

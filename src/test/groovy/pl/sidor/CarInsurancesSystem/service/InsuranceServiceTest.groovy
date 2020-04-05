package pl.sidor.CarInsurancesSystem.service

import pl.sidor.CarInsurancesSystem.entity.entities.CarInsurance
import pl.sidor.CarInsurancesSystem.repository.insurance.CarInsuranceRepository
import pl.sidor.CarInsurancesSystem.utils.PolicyNumberGenerator
import spock.lang.Shared
import spock.lang.Specification

class InsuranceServiceTest extends Specification {

    CarInsuranceRepository carInsuranceRepository = Mock(CarInsuranceRepository.class)
    PolicyNumberGenerator policyNumberGenerator=Mock(PolicyNumberGenerator.class)
    InsuranceService carInsuranceService = [carInsuranceRepository, policyNumberGenerator]

    @Shared
    String name = "Name"
    @Shared
    String lastName = "lastName"

    def "should return CarInsurance List"() {
        given:
        CarInsurance carInsurance = Stub()

        when:
        carInsuranceRepository.findByPersonNameAndPersonLastName(name, lastName) >> [carInsurance]
        def result = carInsuranceService.findByPersonData(name, lastName)

        then:
        result != null
        result.size() == 1
    }

    def "should return  empty CarInsurance List"() {
        when:
        carInsuranceRepository.findByPersonNameAndPersonLastName(name, lastName) >> []
        def result = carInsuranceService.findByPersonData(name, lastName)

        then:
        result != null
        result.size() == 0
    }
}

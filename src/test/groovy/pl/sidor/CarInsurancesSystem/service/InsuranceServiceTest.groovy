package groovy.pl.sidor.CarInsurancesSystem.service

import generated_class.model.CarInsuranceRequest
import generated_class.model.CheckCarInsuranceRequest
import pl.sidor.CarInsurancesSystem.entity.entities.Car
import pl.sidor.CarInsurancesSystem.entity.entities.CarInsurance
import pl.sidor.CarInsurancesSystem.entity.entities.Person
import pl.sidor.CarInsurancesSystem.exception.InsuranceException
import pl.sidor.CarInsurancesSystem.repository.CarRepository
import pl.sidor.CarInsurancesSystem.repository.insurance.CarInsuranceRepository
import pl.sidor.CarInsurancesSystem.service.InsuranceQueryService
import pl.sidor.CarInsurancesSystem.service.InsuranceService
import pl.sidor.CarInsurancesSystem.utils.PolicyNumberGenerator
import spock.lang.Shared
import spock.lang.Specification

class InsuranceServiceTest extends Specification {

    CarInsuranceRepository carInsuranceRepository = Mock(CarInsuranceRepository.class)
    PolicyNumberGenerator policyNumberGenerator = Mock(PolicyNumberGenerator.class)
    CarRepository carRepository = Mock(CarRepository.class)
    InsuranceService carInsuranceService = [carInsuranceRepository, policyNumberGenerator, carRepository]
    InsuranceQueryService insuranceQueryService = [carInsuranceRepository]

    @Shared
    String name = "Name"
    @Shared
    String lastName = "lastName"
    @Shared
    String number = "LUB997"

    def "should return CarInsurance List"() {
        given:
        CarInsurance carInsurance = Stub()

        when:
        carInsuranceRepository.findByPersonNameAndPersonLastName(name, lastName) >> [carInsurance]
        def result = insuranceQueryService.findByPersonData(name, lastName)

        then:
        result != null
        result.size() == 1
    }

    def "should return  empty CarInsurance List"() {
        when:
        carInsuranceRepository.findByPersonNameAndPersonLastName(name, lastName) >> []
        def result = insuranceQueryService.findByPersonData(name, lastName)

        then:
        result != null
        result.size() == 0
    }

    def "should MapAndSaveInsurance"() {
        given:
        CarInsuranceRequest carInsuranceRequest = Stub()
        CarInsurance carInsurance = prepareCarInsurance()
        carInsurance.getCar().setId(1L)

        when:
        carInsuranceRepository.save(_ as CarInsurance) >> carInsurance
        carRepository.findById(_ as Long) >> Optional.of(Car.builder().build())
        def result = carInsuranceService.mapAndSaveInsurance(carInsuranceRequest)

        then:
        result != null
    }

    def "should find CarInsurance by RegistryNumber"() {
        given:
        CheckCarInsuranceRequest request = new CheckCarInsuranceRequest()
        request.setRegistryNumber(number)
        CarInsurance carInsurance = Stub()

        when:
        carInsuranceRepository.findByCarRegistryNumber(_ as String) >> [carInsurance]
        def result = insuranceQueryService.findInsuranceByCarRegistryNumber(request)

        then:
        result != null
        request.getRegistryNumber() == number
    }

    def "should  throw exception when find CarInsurance by RegistryNumber"() {
        given:
        CheckCarInsuranceRequest request = new CheckCarInsuranceRequest()
        request.setRegistryNumber(number)

        when:
        carInsuranceRepository.findByCarRegistryNumber(_ as String) >> []
        insuranceQueryService.findInsuranceByCarRegistryNumber(request)

        then:
        thrown(InsuranceException.class)
    }

    private static CarInsurance prepareCarInsurance() {
        CarInsurance.builder()
                .car(Car.builder().build())
                .person(Person.builder().build())
                .build()
    }
}

package pl.sidor.CarInsurancesSystem.service

import pl.sidor.CarInsurancesSystem.entity.entities.Car
import pl.sidor.CarInsurancesSystem.entity.entities.CarInsurance
import spock.lang.Specification

class MapperServiceTest extends Specification {

    MapperService mapperService = []

    def " should MapCarInsurance"() {
        given:
        CarInsurance carInsurance = Stub()

        when:
        def result = mapperService.mapCarInsurance(carInsurance)

        then:
        result != null
    }

    def "should MapCarToResponse"() {
        given:
        Car car = Stub()

        when:
        def result = mapperService.mapCarToResponse(car)

        then:
        result != null
    }
}

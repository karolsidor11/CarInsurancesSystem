package pl.sidor.CarInsurancesSystem.validations.predicate

import generated_class.model.Car
import generated_class.model.CarInsuranceRequest
import spock.lang.Specification
import spock.lang.Unroll

class CarPredicateTest extends Specification {

    CarPredicate carPredicate = []

    @Unroll
    def "should validate Car request"() {
        given:
        CarInsuranceRequest request = new CarInsuranceRequest()
        Car car = prepareCar(mark, model, registryNumber)
        request.setCar(car)

        when:
        boolean result = carPredicate.test(request)

        then:
        result == expectedResult

        where:
        mark   | model | registryNumber | expectedResult
        null   | null  | null           | true
        "Audi" | null  | null           | true
        "Audi" | "A6"  | null           | true
        "Audi" | "A6"  | "LUB997"       | false
    }

    private static Car prepareCar(String mark, String model, String registryNumber) {
        Car car = new Car()
        car.setMark(mark)
        car.setModel(model)
        car.setRegistryNumber(registryNumber)
        return car
    }
}

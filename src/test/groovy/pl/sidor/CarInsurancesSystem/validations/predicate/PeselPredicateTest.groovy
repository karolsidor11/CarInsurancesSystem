package pl.sidor.CarInsurancesSystem.validations.predicate

import spock.lang.Specification
import spock.lang.Unroll

class PeselPredicateTest extends Specification {

    PeselPredicate peselPredicate = []

    @Unroll
    def "should validate PESEL"() {
        given:
        BigInteger pesel = Pesel

        when:
        def result = peselPredicate.test(pesel)

        then:
        result == expectedResult

        where:
        Pesel                            | expectedResult
        null                             | true
        BigInteger.valueOf(1)            | true
        BigInteger.valueOf(123456789)    | true
        BigInteger.valueOf(12345678911)  | false
        BigInteger.valueOf(123456789112) | true
    }
}

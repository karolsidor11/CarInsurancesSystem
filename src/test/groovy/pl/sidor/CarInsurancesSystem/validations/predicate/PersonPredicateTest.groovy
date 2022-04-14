package groovy.pl.sidor.CarInsurancesSystem.validations.predicate

import generated_class.model.Person
import pl.sidor.CarInsurancesSystem.validations.predicate.PersonPredicate
import spock.lang.Specification
import spock.lang.Unroll

class PersonPredicateTest extends Specification {

    PersonPredicate personPredicate = []

    @Unroll
    def "should validate Person where #name  #lastName"() {
        given:
        Person person = getPerson(name, lastName)

        when:
        def result = personPredicate.test(person)

        then:
        result == expectedResult

        where:
        name  | lastName | expectedResult
        null  | null     | true
        null  | ""       | true
        ""    | null     | true
        ""    | ""       | true
        "Jan" | "Nowak"  | false
    }

    private static Person getPerson(String name, String lastName) {
        Person person = new Person()
        person.setName(name)
        person.setLastName(lastName)
        return person
    }
}

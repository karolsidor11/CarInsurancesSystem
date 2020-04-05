package pl.sidor.CarInsurancesSystem.utils

import spock.lang.Specification

class PolicyNumberGeneratorTest extends Specification {

    PolicyNumberGenerator policyNumberGenerator = []

    def "should generate PolicyNumber"() {
        when:
        def policyNumber = policyNumberGenerator.generatePolicyNumber()

        then:
        policyNumber != null
        policyNumber.size()==20
    }
}

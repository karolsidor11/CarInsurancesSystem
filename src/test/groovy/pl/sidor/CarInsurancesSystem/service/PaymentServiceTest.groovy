package pl.sidor.CarInsurancesSystem.service

import generated_class.model.PaymentCarInsuranceResponse
import pl.sidor.CarInsurancesSystem.entity.entities.PaymentCarInsurance
import pl.sidor.CarInsurancesSystem.repository.PaymentCarInsuranceRepository
import spock.lang.Specification

class PaymentServiceTest extends Specification {

    PaymentCarInsuranceRepository paymentCarInsuranceRepository = Mock()
    PaymentService paymentService = [paymentCarInsuranceRepository]

    def "should save Payment for Insurance"() {
        given:
        PaymentCarInsuranceResponse payments = Stub()
        PaymentCarInsurance paymentCarInsurance = Stub()

        when:
        paymentCarInsuranceRepository.save(_) >> paymentCarInsurance
        paymentService.savePaymentCarInsurance(payments)

        then:
        1 * paymentCarInsuranceRepository.save(_)
    }
}

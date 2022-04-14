package groovy.pl.sidor.CarInsurancesSystem.validations.predicate


import generated_class.model.CarInsuranceRequest
import pl.sidor.CarInsurancesSystem.validations.predicate.DatesPredicate
import spock.lang.Specification
import spock.lang.Unroll

import javax.xml.datatype.DatatypeFactory
import javax.xml.datatype.XMLGregorianCalendar

class DatesPredicateTest extends Specification {

    DatesPredicate datesPredicate = []

    @Unroll
    def "should validate Dates"() {
        given:
        CarInsuranceRequest request = getRequest(dateFrom, dateTo)

        when:
        def result = datesPredicate.test(request)

        then:
        result == expectedResult

        where:
        dateFrom                        | dateTo                          | expectedResult
        null                            | null                            | true
        "2020-05-12T13:18:59.003+02:00" | "2020-04-12T13:18:59.003+02:00" | true
        "2020-04-12T13:18:59.003+02:00" | "2020-05-12T13:18:59.003+02:00" | false
    }

    private static CarInsuranceRequest getRequest(String dateFrom, String dateTo) {
        CarInsuranceRequest carInsuranceRequest = new CarInsuranceRequest()
        if (Objects.isNull(dateFrom) || Objects.isNull(dateTo)) {
            return carInsuranceRequest;
        }
        XMLGregorianCalendar dateOne = DatatypeFactory.newInstance().newXMLGregorianCalendar(dateFrom)
        XMLGregorianCalendar dateTwo = DatatypeFactory.newInstance().newXMLGregorianCalendar(dateTo)
        carInsuranceRequest.setDateTo(dateTwo)
        carInsuranceRequest.setDateFrom(dateOne)

        return carInsuranceRequest
    }
}

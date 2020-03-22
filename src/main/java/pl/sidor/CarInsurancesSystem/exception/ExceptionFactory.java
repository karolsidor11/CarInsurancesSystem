package pl.sidor.CarInsurancesSystem.exception;

public final class ExceptionFactory {

    public static InsuranceException nieoczekiwanyBladSystemu() {
        return new InsuranceException(MessageException.NIEOCZEKIWANY_BLAD.name());
    }
}

package pl.sidor.CarInsurancesSystem.exception;

public final class ExceptionFactory {

    public static InsuranceException create(MessageException message) {
        return new InsuranceException(message.getMessage());
    }

    public static InsuranceException brakUbezpieczeniaSamochodu() {
        return new InsuranceException(MessageException.NIE_ZNALEZIONO_UBEZPIECZENIA.getMessage());
    }
}

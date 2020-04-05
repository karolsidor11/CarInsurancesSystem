package pl.sidor.CarInsurancesSystem.exception;

public final class ExceptionFactory {

    public static InsuranceException nieoczekiwanyBladSystemu() {
        return new InsuranceException(MessageException.NIEOCZEKIWANY_BLAD.name());
    }

    public static InsuranceException brakUbezpieczeniaSamochodu(){
        return new InsuranceException(MessageException.NIE_ZNALEZIONO_UBEZPIECZENIA.getMessage());
    }

    public static InsuranceException brakPlatnosci(){
        return  new InsuranceException(MessageException.BRAK_PLATNOSCI.getMessage());
    }
}

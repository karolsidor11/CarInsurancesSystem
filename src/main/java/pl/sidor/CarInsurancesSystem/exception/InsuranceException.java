package pl.sidor.CarInsurancesSystem.exception;

import lombok.Getter;

import java.util.function.Supplier;

@Getter
public class InsuranceException extends Exception implements Supplier<Throwable> {

    private final String message;

    InsuranceException(String message) {
        this.message = message;
    }

    public InsuranceException(String message, Throwable cause, String message1) {
        super(message, cause);
        this.message = message1;
    }

    @Override
    public Throwable get() {
        return new InsuranceException(message);
    }
}

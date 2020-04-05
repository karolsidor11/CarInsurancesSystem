package pl.sidor.CarInsurancesSystem.validations.predicate;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Objects;
import java.util.function.Predicate;

@Component
public class PeselPredicate implements Predicate<BigInteger> {

    @Override
    public boolean test(BigInteger validatedObject) {
        return Objects.isNull(validatedObject);
    }
}

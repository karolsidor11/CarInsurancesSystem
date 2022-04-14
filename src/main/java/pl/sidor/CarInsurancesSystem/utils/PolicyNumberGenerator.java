package pl.sidor.CarInsurancesSystem.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class PolicyNumberGenerator {

    private static final String SINGS = "1234567890";

    public String generatePolicyNumber() {
        StringBuilder policyNumber = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            int number = random.nextInt(SINGS.length());
            policyNumber.append(SINGS.substring(number, number + 1));
        }

        return policyNumber.toString();
    }
}

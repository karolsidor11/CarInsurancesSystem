package pl.sidor.CarInsurancesSystem.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class PolicyNumberGenerator {

    public String generatePolicyNumber() {
        StringBuilder policyNumber = new StringBuilder();
        String sings = "1234567890";
        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            int number = random.nextInt(sings.length());
            policyNumber.append(sings.substring(number, number + 1));
        }

        return policyNumber.toString();
    }
}

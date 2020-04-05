package pl.sidor.CarInsurancesSystem.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.sidor.CarInsurancesSystem.endpoint.CarInsuranceVerificationEndpoint;
import pl.sidor.CarInsurancesSystem.service.InsuranceService;
import pl.sidor.CarInsurancesSystem.service.MapperService;

@Configuration
public class ApplicationConfig {

    @Bean
    public CarInsuranceVerificationEndpoint carInsuranceVerificationEndpoint(InsuranceService service, MapperService mapperService) {
        return new CarInsuranceVerificationEndpoint(service, mapperService);
    }
}

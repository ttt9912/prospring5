package ch4.p8_profiles.with_configuration;

import ch4.p8_profiles.FoodProvierService;
import ch4.p8_profiles.kindergarden.FoodProviderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("kindergarden")
public class KindergardenConfig {

    @Bean
    public FoodProvierService foodProviderService(){
        // kindergarden.FoodProviderServiceImpl
        return new FoodProviderServiceImpl();
    }
}

package ch4.p8_profiles.with_configuration;

import ch4.p8_profiles.FoodProvierService;
import ch4.p8_profiles.highschool.FoodProviderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("highschool")
public class HighschoolConfig {

    @Bean
    public FoodProvierService foodProviderService(){
        // highschool.FoodProviderServiceImpl
        return new FoodProviderServiceImpl();
    }
}

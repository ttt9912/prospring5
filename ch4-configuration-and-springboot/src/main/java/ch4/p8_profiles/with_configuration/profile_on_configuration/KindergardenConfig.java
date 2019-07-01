package ch4.p8_profiles.with_configuration.profile_on_configuration;

import ch4.p8_profiles.beans.FoodProvierService;
import ch4.p8_profiles.beans.kindergarden.FoodProviderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("kindergarden")
class KindergardenConfig {

    @Bean
    public FoodProvierService foodProviderService(){
        // kindergarden.FoodProviderServiceImpl
        return new FoodProviderServiceImpl();
    }
}

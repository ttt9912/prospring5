package ch4.p8_profiles.with_configuration.profile_on_component;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("kindergarden")
public class KindergardenFoodProvider implements FoodProvider {
}

package ch4.p8_profiles.with_configuration.profile_on_component;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("highschool")
public class HighschoolFoodProvider implements FoodProvider {
}

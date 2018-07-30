package ch4.p8_profiles.with_configuration;


import ch4.p8_profiles.Food;
import ch4.p8_profiles.FoodProvierService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/*
 * same as with context xml
 */
public class Demo {

    // run via run configuration! ('ch4_p8_cfg_profile=highschool' or 'ch4_p8_cfg_profile=kindergarden')
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
                HighschoolConfig.class,
                KindergardenConfig.class);


        final FoodProvierService foodProvierService = ctx.getBean("foodProviderService", FoodProvierService.class);

        final List<Food> foods = foodProvierService.provideLunchSet();
        foods.forEach(System.out::println);


        System.out.println("Active profiles: " + getActiveProfiles(ctx));

        ctx.close();
    }

    private static String getActiveProfiles(final ApplicationContext ctx) {
        String[] activeProfiles = ctx.getEnvironment().getActiveProfiles();
        return String.join(",", activeProfiles);
    }
}

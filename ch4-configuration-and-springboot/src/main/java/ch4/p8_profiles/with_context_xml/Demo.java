package ch4.p8_profiles.with_context_xml;

import ch4.p8_profiles.beans.Food;
import ch4.p8_profiles.beans.FoodProvierService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;

/*
 * FoodProviderService has two implementations, one in highschool and one in kindergarden.
 *
 * App has two profiles, e.g. two context xmls. Each one uses one of the implementations.
 *
 * Profile is set via VM Argument -Dspring.profiles.active="highschool"
 * or programmatically via ctx.getEnvironment().setActiveProfiles("kindergarden");
 */
class Demo {

    // run via run configuration! ('ch4_p8_ctx_profile=highschool' or 'ch4_p8_ctx_profile=kindergarden')
    public static void main(String[] args){

        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("*_food_context.xml"); // loads both
        ctx.refresh();


        final FoodProvierService foodProvierService = ctx.getBean("foodProviderService", FoodProvierService.class);

        final List<Food> foods = foodProvierService.provideLunchSet();
        foods.forEach(System.out::println);


        // profile is set in run config via VM argument
        System.out.println("Active profiles: " + getActiveProfiles(ctx));

        ctx.close();

    }

    private static String getActiveProfiles(final ApplicationContext ctx) {
        String[] activeProfiles = ctx.getEnvironment().getActiveProfiles();
        return String.join(",", activeProfiles);
    }

}

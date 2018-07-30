package ch4.p8_profiles.with_context_xml;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;

public class Demo {

    /*
     * FoodProviderService has two implementations, one in highschool and one in kindergarden.
     *
     * App has two profiles, e.g. two context xmls. Each one uses one of the implementations.
     *
     * Profile is set via VM Argument -Dspring.profiles.active="highschool"
     * or programmatically via ctx.getEnvironment().setActiveProfiles("kindergarden");
     */

    public static void main(String[] args){

        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("*_food_context.xml"); // loads both
        ctx.refresh();

        final FoodProvierService foodProvierService = ctx.getBean("foodProviderService", FoodProvierService.class);

        System.out.println("Profile: highschool");


        // profile is set in run config via VM argument
        final List<Food> foods = foodProvierService.provideLunchSet();
        foods.forEach(System.out::println);

        ctx.close();

    }

}

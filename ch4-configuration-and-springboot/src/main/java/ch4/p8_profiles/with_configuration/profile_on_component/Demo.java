package ch4.p8_profiles.with_configuration.profile_on_component;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Demo {

    /*
     * run via run configuration! ('profile=highschool' or 'profile=kindergarden')
     */
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);

        System.out.println("Active profiles: " + getActiveProfiles(ctx));

        System.out.println("FoodProvider beans:");
        ctx.getBeansOfType(FoodProvider.class).values()
                .forEach(bean -> System.out.println(bean.getClass().getSimpleName()));
    }

    private static String getActiveProfiles(final ApplicationContext ctx) {
        String[] activeProfiles = ctx.getEnvironment().getActiveProfiles();
        return String.join(",", activeProfiles);
    }
}

package ch3.apps.p7_bean_naming.with_configuration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Map;

/*
 * Bean Aliasing:
 * Der @Bean Annotation wird ein String array übergeben,
 * der erste String ist die id, die restlichen sind Aliase.
 */

class BeanAliasing {

    @Configuration
    @ComponentScan("ch3.apps.p7_bean_naming")
    static class Config {
        @Bean(name = {"singer", "john", "johnny"})
        public Singer singer() {
            return new Singer();
        }
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);

        Map<String, Singer> beans = ctx.getBeansOfType(Singer.class);
        beans.forEach((key, value) -> System.out.println("id: " + key
                + "\naliases: " + Arrays.toString(ctx.getAliases(key)) + "\n"));

        ctx.close();
    }
}

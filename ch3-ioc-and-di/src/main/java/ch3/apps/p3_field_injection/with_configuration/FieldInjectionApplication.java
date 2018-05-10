package ch3.apps.p3_field_injection.with_configuration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/*
 * Field Injection: dependency wird direkt ins Field injected (@Autowired).
 * Kein Konstruktor oder Setter ist mehr nötig.
 *
 */

public class FieldInjectionApplication {

    public static void main(String... args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        Singer singerBean = context.getBean(Singer.class);
        singerBean.sing();
    }
}

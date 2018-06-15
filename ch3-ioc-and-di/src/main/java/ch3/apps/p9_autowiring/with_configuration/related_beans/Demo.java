package ch3.apps.p9_autowiring.with_configuration.related_beans;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/*
 * Autowiring von Verwandten Beans
 * - 2 Verwandte Beans: @Primary auf Klassenebene
 */
class Demo {

    @Configuration
    @ComponentScan("ch3.apps.p9_autowiring.with_configuration.related_beans")
    static class Config {}

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Demo.Config.class);

        Target bean = ctx.getBean(Target.class);
        System.out.println(bean);

        ctx.close();
    }
}

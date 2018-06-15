package ch3.apps.p9_autowiring.with_configuration.simple_beans;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/*
 * Autowiring mit Annotationen ist per Default byType
 *
 * Mit Annotationen kann man nur eine Konfiguration pro bean verwenden.
 * Also nicht byName, byType, constructor für dieselbe Klasse, wie das
 * mit context.xml möglich ist.
 */
class Demo {

    @Configuration
    @ComponentScan("ch3.apps.p9_autowiring.with_configuration.simple_beans")
    static class Config {}

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Demo.Config.class);

        Target bean = ctx.getBean(Target.class);
        System.out.println(bean);

        ctx.close();
    }
}

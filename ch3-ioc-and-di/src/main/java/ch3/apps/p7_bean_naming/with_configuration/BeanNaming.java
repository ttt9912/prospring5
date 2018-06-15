package ch3.apps.p7_bean_naming.with_configuration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Map;


class BeanNaming {

    @Configuration
    @ComponentScan("ch3.apps.p7_bean_naming")
    static class Config {}

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);

        Map<String, Singer> beans = ctx.getBeansOfType(Singer.class);
        beans.forEach((key, value) -> System.out.println("id: " + key));

        ctx.close();
    }
}

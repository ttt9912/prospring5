package ch3.apps.p9_autowiring.with_configuration.related_beans;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/*
 * Bean definition via Configuration
 */
class Demo2 {

    @Configuration
    static class Config {

        @Bean
        public Foo fooOne(){
            System.out.println("@Bean fooOne called");
            return new FooOne();
        }

        @Bean
        public Foo fooTwo(){
            System.out.println("@Bean fooTwo called");
            return new FooTwo();
        }

        @Bean
        public Target target(){
            System.out.println("@Bean target called");
            return new Target();
        }
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Demo2.Config.class);

        Target bean = ctx.getBean(Target.class);
        System.out.println(bean);

        ctx.close();
    }
}

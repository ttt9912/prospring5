package ch3.apps.p5_lookup_method_injection.with_configuration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

class LookupDemo {

    @Configuration
    @ComponentScan("ch3.apps.p5_lookup_method_injection.with_configuration")
    static class Config {
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);


        System.out.println("- Setter Injection -");
        DemoBean standardLookupBean = ctx.getBean("standardLookupBean", DemoBean.class);
        compareBeans("standardLookupBean", standardLookupBean);


        System.out.println("- Lookup Method Injection -");
        DemoBean abstractLookupBean = ctx.getBean("abstractLookupBean", DemoBean.class);
        compareBeans("abstractLookupBean", abstractLookupBean);

        ctx.close();
    }

    private static void compareBeans(String beanName, DemoBean standardLookupBean) {
        Singer singer1 = standardLookupBean.getMySinger();
        Singer singer2 = standardLookupBean.getMySinger();

        System.out.println("Singer instances are the same for " + beanName + "? " + (singer1 == singer2));
        System.out.println("Singer 1: " + singer1.hashCode());
        System.out.println("Singer 2: " + singer2.hashCode());
        System.out.println();
    }
}

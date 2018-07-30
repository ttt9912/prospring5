package ch4.p7_configuration_classes;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class App {

    @Test
    void configuration_beanInstantiation() {
        // AppconfigOne benutzt @Bean zur bean instantiation
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfigOne.class);

        MessageRenderer messageRenderer = ctx.getBean("messageRenderer", MessageRenderer.class);
        messageRenderer.render();
    }

    @Test
    void configuration_autowiring() {
        // AppConfigTwo benutzt Components, @ComponentScan und @Autowire zur bean instantiation
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfigTwo.class);

        MessageRenderer messageRenderer = ctx.getBean("messageRenderer", MessageRenderer.class);
        messageRenderer.render();
    }

    @Test
    void configuration_mixedConfiguration() {
        // AppConfigThree importiert AppConfigFour sowie mixed_context.xml
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfigThree.class);

        MessageRenderer messageRenderer = ctx.getBean("messageRenderer", MessageRenderer.class);
        messageRenderer.render();
    }
}

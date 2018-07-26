package ch4.p7_configuration_classes;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class App {

    @Test
    void configuration_beanInstantiation(){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfigOne.class);

        MessageRenderer messageRenderer = ctx.getBean("messageRenderer", MessageRenderer.class);
        messageRenderer.render();
    }

    @Test
    void configuration_autowiring(){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfigTwo.class);

        MessageRenderer messageRenderer = ctx.getBean("messageRenderer", MessageRenderer.class);
        messageRenderer.render();
    }

    @Test
    void configuration_mixedConfiguration(){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfigThree.class);

        MessageRenderer messageRenderer = ctx.getBean("messageRenderer", MessageRenderer.class);
        messageRenderer.render();
    }
}

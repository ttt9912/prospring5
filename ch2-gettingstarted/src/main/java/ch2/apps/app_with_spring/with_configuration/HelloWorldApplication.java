package ch2.apps.app_with_spring.with_configuration;

import ch2.beans.MessageRenderer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Context mit Configuration (alternativ: xml file)
 */

class HelloWorldApplication {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(HelloWorldConfiguration.class);
        MessageRenderer mr = context.getBean("renderer", MessageRenderer.class); // zweiter parameter ist für das Casting
        mr.render();
    }
}

package ch3.apps.p1_setter_injection.with_configuration_class;

import ch3.beans.MessageRenderer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class HelloWorldApplication {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(HelloWorldConfiguration.class);
        MessageRenderer mr = context.getBean("renderer", MessageRenderer.class); // zweiter parameter ist für das Casting
        mr.render();
    }
}

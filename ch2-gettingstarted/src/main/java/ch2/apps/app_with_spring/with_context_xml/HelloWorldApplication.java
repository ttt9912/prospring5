package ch2.apps.app_with_spring.with_context_xml;

import ch2.beans.MessageRenderer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Context mit XML file (alternativ: Configuration)
 */

public class HelloWorldApplication {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        MessageRenderer mr = context.getBean("renderer", MessageRenderer.class); // zweiter parameter ist für das Casting
        mr.render();
    }
}

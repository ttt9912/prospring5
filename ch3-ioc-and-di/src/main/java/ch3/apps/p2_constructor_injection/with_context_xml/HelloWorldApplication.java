package ch3.apps.p2_constructor_injection.with_context_xml;

import ch3.beans.MessageRenderer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Context mit XML file (alternativ: Configuration)
 */

class HelloWorldApplication {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("constructorInjection_context.xml");
        MessageRenderer mr = context.getBean("renderer", MessageRenderer.class);
        mr.render();
    }
}

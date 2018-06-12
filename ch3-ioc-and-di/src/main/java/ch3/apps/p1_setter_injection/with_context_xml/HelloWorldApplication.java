package ch3.apps.p1_setter_injection.with_context_xml;

import ch3.beans.MessageRenderer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class HelloWorldApplication {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("setterInjection_context.xml");
        MessageRenderer mr = context.getBean("renderer", MessageRenderer.class);
        mr.render();
    }
}

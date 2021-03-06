package ch4.p6_application_events.with_context_xml;

import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

/*
 * ApplicationContext kann als broker verwendet werden und events publizieren und erhalten.
 * Für kleine Events (z.B. Product details update) zweckmässiger als JMS.
 */
public class Demo {

    @Test
    public void applicationEvents() {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("application_events_context.xml");
        ctx.refresh();

        MessageEventPublisher eventPublisher = (MessageEventPublisher) ctx.getBean("eventPublisher");
        eventPublisher.publish("Hello");
        eventPublisher.publish("World");

        ctx.close();
    }
}

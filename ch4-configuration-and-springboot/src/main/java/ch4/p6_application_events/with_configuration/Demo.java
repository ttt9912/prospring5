package ch4.p6_application_events.with_configuration;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/*
 * ApplicationContext kann als broker verwendet werden und events publizieren und erhalten.
 * Für kleine Events (z.B. Product details update) zweckmässiger als JMS.
 */
public class Demo {

    @Test
    public void applicationEvents() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppEventsConfig.class);

        MessageEventPublisher messageEventPublisher = (MessageEventPublisher) ctx.getBean("messageEventPublisher");
        messageEventPublisher.publish("Hello");
        messageEventPublisher.publish("World");
    }
}

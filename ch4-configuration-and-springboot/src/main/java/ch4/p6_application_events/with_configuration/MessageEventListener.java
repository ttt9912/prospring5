package ch4.p6_application_events.with_configuration;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/*
 * Jedes Bean kann Events empfangen indem es ApplicationListener<T> implementiert
 */
@Component
class MessageEventListener implements ApplicationListener<MessageEvent> {

    // automatisch aufgerufen, wenn ein Event kommt
    @Override
    public void onApplicationEvent(final MessageEvent event) {
        System.out.println("MessageEventListener received event: " + event);
    }


}

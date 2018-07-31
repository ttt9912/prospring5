package ch4.p6_application_events;

import org.springframework.context.ApplicationListener;

/*
 * Jedes Bean kann Events empfangen indem es ApplicationListener<T> implementiert
 */
class MessageEventListener implements ApplicationListener<MessageEvent> {

    // automatisch aufgerufen, wenn ein Event kommt
    @Override
    public void onApplicationEvent(final MessageEvent event) {
        System.out.println("MessageEventListener received event: " + event);
    }
}

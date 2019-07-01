package ch4.p6_application_events.with_configuration;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
class MessageEventListenerWithAnnotation {

    @EventListener(MessageEvent.class)
    public void onApplicationEvent(final MessageEvent event) {
        System.out.println("MessageEventListenerWithAnnotation received event: " + event);
    }


}

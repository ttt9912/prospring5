package ch4.p6_application_events.with_configuration;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/*
 * Publishen von Events geht über den ApplicationContext.
 * Publisher bean braucht Zugang zum ApplicationContext, daher ApplicationContextAware
 */
@Component
class MessageEventPublisher implements ApplicationContextAware {
    private ApplicationContext ctx;

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        this.ctx = applicationContext;
    }

    // publish
    void publish(String message) {
        System.out.println("MessageEventPublisher publishing event with message: " + message);
        ctx.publishEvent(new MessageEvent(this, message));
    }
}

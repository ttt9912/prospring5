package ch9.p5_springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/*
 * Artemis JMS server creates an embedded queue named 'singers'.
 * Configured in application.properties
 */
@Component
class Messages {
    private static Logger logger = LoggerFactory.getLogger(Messages.class);

    @JmsListener(destination = "singers")
    public void onMessage(String content) {
        logger.info("Received JMS content: {}", content);
    }
}

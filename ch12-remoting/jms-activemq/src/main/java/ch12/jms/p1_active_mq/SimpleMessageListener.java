package ch12.jms.p1_active_mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

@Component("messageListener")
class SimpleMessageListener {
    private static final Logger logger = LoggerFactory.getLogger(SimpleMessageListener.class);

    /*
     * Annotated JMS listener methods are allowed to have flexible signatures similar
     * to what provides: Message, etc. (See @JmsListener javadoc for details)
     */

    @JmsListener(destination = "DEMO-JMS-QUEUE", containerFactory = "jsaFactory")
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;

        try {
            logger.info(textMessage.getText());
        } catch (JMSException e) {
            logger.error("Jms Error", e);
        }

    }
}

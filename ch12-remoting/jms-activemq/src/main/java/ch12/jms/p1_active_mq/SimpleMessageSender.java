package ch12.jms.p1_active_mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.TextMessage;

/*
 * JmsTemplate.send(): creates an ad-hoc implementation of MessageCreator (lambda)
 *                     MessageCreator is implemented to create a new instance of TextMessage
 */
@Component("messageSender")
class SimpleMessageSender implements MessageSender {
    private static final Logger logger = LoggerFactory.getLogger(SimpleMessageSender.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    public void sendMessage(final String message) {
        jmsTemplate.send(session -> {
            TextMessage textMessage = session.createTextMessage(message);
            logger.info("sending message: {}", message);
            return textMessage;
        });
    }
}

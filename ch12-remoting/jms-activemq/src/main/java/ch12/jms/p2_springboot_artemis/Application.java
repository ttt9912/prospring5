package ch12.jms.p2_springboot_artemis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.io.IOException;

@SpringBootApplication
public class Application {


    /*
     * DOES NOT WORK
     */

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Bean
    public JmsListenerContainerFactory<?> containerFactory(
            ConnectionFactory connectionFactory,
            DefaultJmsListenerContainerFactoryConfigurer configurer) {

        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext ctx =
                SpringApplication.run(Application.class, args);

        JmsTemplate jmsTemplate = ctx.getBean(JmsTemplate.class);
        jmsTemplate.setDeliveryDelay(3000L);

        for (int i = 0; i < 10; ++i) {
            logger.info("sending test message...");
            jmsTemplate.convertAndSend("prospring5",
                    "Testmessage: " + i);
        }

        System.in.read();
        ctx.close();
    }

    @JmsListener(destination = "prospring5", containerFactory = "containerFactory")
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;

        try {
            logger.info("received message: {}", textMessage.getText());
        } catch (JMSException e) {
            logger.error("JMS error", e);
        }
    }
}


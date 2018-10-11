package ch12.amqp.rabbitmq.with_configuration;

import org.junit.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

/*
 * RabbitMQ: open source message broker software (sometimes called message-oriented middleware)
 *           implements the Advanced Message Queuing Protocol (AMQP)
 *
 *
 */
public class Demo {

    /*
     * RabbitMQ Docker must be running!
     */

    @Test
    public void rabbit() throws IOException {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(RabbitMQConfig.class);

        RabbitTemplate rabbitTemplate = ctx.getBean(RabbitTemplate.class);
        rabbitTemplate.convertAndSend("FL");

        System.in.read();
        ctx.close();
    }
}

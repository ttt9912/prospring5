package ch12.amqp.rabbitmq.with_context_xml;

import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

/*
 * RabbitMQ: open source message broker software (sometimes called message-oriented middleware)
 *           implements the Advanced Message Queuing Protocol (AMQP)
 */
public class Demo {

    // TODO: NOT WORKING

    /*
     * RabbitMQ Docker must be running!
     */

    @Test
    public void rabbit() {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("amqp-rpc-app-context.xml");
        ctx.refresh();

        WeatherService weatherService = ctx.getBean(WeatherService.class);
    }
}

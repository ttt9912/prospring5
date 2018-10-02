package ch12.jms.p1_active_mq;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/*
 * ActiveMQ: JMS 2.0 implementation
 */
class Demo {

    /*
     * ActiveMQ Docker must be running!
     */

    @Test
    void jms() {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(AppConfig.class);

        MessageSender messageSender =
                ctx.getBean("messageSender", MessageSender.class);

        messageSender.sendMessage("Hello");
        messageSender.sendMessage("Goodbye");

        ctx.close();
    }
}

package ch4.p3_factory_beans;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

/*
 * Factory Beans werden verwendet zur Erzeugung von Beans, die nicht mit
 * new instanziiert werden können.
 *
 * MessageDigester braucht eine Instanz von java.security.MessageDigest, welche nicht
 * mit new Instanziiert werden kann. Daher kann sie Spring nicht selber injecten.
 * FactoryBean regelt die Erzeugung der MessageDigest Instanz.
 *
 * In context.xml wird für das MessageDigest Bean anstatt der Klasse MessageDigest die Klasse
 * MessageDigestFactoryBean, welche nicht ein Bean von sich selbst erzeugt sondern von MessageDigest.
 */
class Demo {

    @Test
    void factoryBean_withContextXml() {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("factoryBeans_context.xml");
        ctx.refresh();

        MessageDigester digester = ctx.getBean("digester", MessageDigester.class);
        digester.digest("Hello World!");

        ctx.close();
    }

    @Test
    void factoryBean_withConfiguration() {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(MessageDigesterConfiguration.class);

        MessageDigester digester = (MessageDigester) ctx.getBean("digester");
        digester.digest("Hello World!");

        ctx.close();
    }

    @Configuration
    static class MessageDigesterConfiguration {

        @Bean
        MessageDigestFactoryBean defaultDigest() {
            return new MessageDigestFactoryBean();
        }

        @Bean
        MessageDigestFactoryBean shaDigest() {
            MessageDigestFactoryBean factory = new MessageDigestFactoryBean();
            factory.setAlgorithmName("SHA1");
            return factory;
        }

        @Bean
        public MessageDigester digester() throws Exception {
            MessageDigester messageDigester = new MessageDigester();
            messageDigester.setDigest1(defaultDigest().getObject()); // getObject() auf dem FactoryBean wird explizit aufgerufen
            messageDigester.setDigest2(shaDigest().getObject()); // getObject() auf dem FactoryBean wird explizit aufgerufen
            return messageDigester;
        }
    }
}

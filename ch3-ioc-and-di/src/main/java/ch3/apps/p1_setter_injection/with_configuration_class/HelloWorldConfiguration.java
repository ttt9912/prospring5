package ch3.apps.p1_setter_injection.with_configuration_class;

import ch3.beans.MessageProvider;
import ch3.beans.MessageRenderer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * @Bean Methoden werden automatisch vom IoC
 * Container aufgerufen
 */

//@ComponentScan("ch.ttt.prospring5.ch3")
@Configuration
class HelloWorldConfiguration {

    @Bean
    public MessageProvider provider() {
        return new HelloWorldMessageProvider();
    }

    @Bean
    public MessageRenderer renderer() {
        MessageRenderer renderer = new StandardOutMessageRenderer();
        renderer.setMessageProvider(provider());
        return renderer;
    }
}

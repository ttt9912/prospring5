package ch2.apps.app_with_spring.with_configuration;

import ch2.beans.HelloWorldMessageProvider;
import ch2.beans.MessageProvider;
import ch2.beans.MessageRenderer;
import ch2.beans.StandardOutMessageRenderer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * @Bean Methoden werden automatisch vom IoC
 * Container aufgerufen
 */

@Configuration
public class HelloWorldConfiguration {

    // equivalent zu <bean id="provider" class="...">
    @Bean
    public MessageProvider provider(){
        return new HelloWorldMessageProvider();
    }

    @Bean
    public MessageRenderer renderer(){
        MessageRenderer renderer = new StandardOutMessageRenderer();
        renderer.setMessageProvider(provider());
        return renderer;
    }
}

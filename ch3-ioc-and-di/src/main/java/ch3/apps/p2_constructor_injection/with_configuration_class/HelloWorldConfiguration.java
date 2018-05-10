package ch3.apps.p2_constructor_injection.with_configuration_class;

import ch3.beans.MessageProvider;
import ch3.beans.MessageRenderer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloWorldConfiguration {

    @Bean
    public MessageProvider provider() {
        return new ConfigurableMessageProvider("my configured message from Configuration class");
    }

    @Bean
    public MessageRenderer renderer() {
        MessageRenderer renderer = new StandardOutMessageRenderer();
        renderer.setMessageProvider(provider());
        return renderer;
    }
}

package ch4.p7_configuration_classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;

/*
 * MixedConfiguration
 *
 * @Import: multiple @Configuration files
 *          (@ComponentScan is imported from AppConfigFour)
 *
 * @ImportResource: import context.xml
 *
 */

@Configuration
@Import(AppConfigFour.class)
@ImportResource(value = "classpath:mixed_context.xml")
public class AppConfigThree {

    @Autowired // Componentscan is defined in AppConfigFour.class
    MessageProvider provider; // provider is defined in context.xml

    @Bean(name = "messageRenderer")
    public MessageRenderer messageRenderer() {
        MessageRenderer renderer = new StandardOutMessageRenderer();
        renderer.setMessageProvider(provider);
        return renderer;
    }
}

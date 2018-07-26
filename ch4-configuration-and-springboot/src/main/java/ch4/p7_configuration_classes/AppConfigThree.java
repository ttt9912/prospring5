package ch4.p7_configuration_classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;

/*
 * MixedConfiguration
 *
 * @Import: multiple @Configuration files
 *          @ComponentScan is imported from AppConfigFour
 *
 * @ImportResource: import context.xml
 */

@Configuration
@Import(AppConfigFour.class)
// TODO @ImportResource(...)...
public class AppConfigThree {

    @Autowired
    MessageProvider messageProviderService;

    @Bean(name = "messageRenderer")
    public MessageRenderer messageRenderer() {
        MessageRenderer renderer = new StandardOutMessageRenderer();
        renderer.setMessageProvider(messageProviderService);
        return renderer;
    }
}

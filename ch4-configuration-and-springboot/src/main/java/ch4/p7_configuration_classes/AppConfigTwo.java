package ch4.p7_configuration_classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

/*
 * AppConfigOne angereichert mit weiteren Annotationen auf Bean declarations TODO: ev. ConfigOne und ConfigTwo konsolidieren.
 *
 * @ComponentScan: scans for Components ()
 * basePackage: can be omitted in this case
 *
 * @Autowired: can be used instead of @Bean if the class is a Component
 */

@Configuration
@ComponentScan(basePackages = {"ch4.p7_configuration_classes"})
class AppConfigTwo {

    @Autowired
    MessageProvider messageProviderService;

    @Bean(name = "messageRenderer")
    public MessageRenderer messageRenderer() {
        MessageRenderer renderer = new StandardOutMessageRenderer();
        renderer.setMessageProvider(messageProviderService);
        return renderer;
    }


}

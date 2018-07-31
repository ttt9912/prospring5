package ch4.p7_configuration_classes;

import ch4.p7_configuration_classes.ConfigurableMessageProvider;
import ch4.p7_configuration_classes.MessageProvider;
import ch4.p7_configuration_classes.MessageRenderer;
import ch4.p7_configuration_classes.StandardOutMessageRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

/*
 * @PropertySource: loads properties into ApplicationContext
 *
 * Environment: Interface representing the environment in which the current application is running.
 * Models two key aspects of the application environment: profiles and properties.
 *
 *
 * weitere Annotationen auf Bean declarations:
 *
 * @Lazy: instantiate bean only when requestet
 * @Scope: bean scope if differend from Singleton
 * @DependsOn: bean depends on other bean; spring instantiates other bean first.
 */

@Configuration
@ComponentScan
@PropertySource(value = "classpath:message.properties")
class AppConfigOne {

    @Autowired
    Environment env; // spring class

    @Bean
    @Lazy
    public MessageProvider messageProvider(){
        // injects properties using constructor injection
        return new ConfigurableMessageProvider(env.getProperty("message"));
    }

    @Bean(name="messageRenderer")
    @Scope(value = "prototype")
    @DependsOn(value = "messageProviderService")
    public MessageRenderer messageRenderer(){
        MessageRenderer renderer = new StandardOutMessageRenderer();
        renderer.setMessageProvider(messageProvider());
        return renderer;
    }


}

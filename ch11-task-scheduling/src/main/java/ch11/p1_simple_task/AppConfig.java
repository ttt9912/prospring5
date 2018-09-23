package ch11.p1_simple_task;

import ch11.config.DataServiceConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan
@Import({DataServiceConfig.class})
public class AppConfig {
}

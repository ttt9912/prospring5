package ch12.httpinvoker.server;

import ch12.httpinvoker.data.DataConfig;
import ch12.httpinvoker.service.ServiceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({DataConfig.class, ServiceConfig.class})
public class AppConfig {
}

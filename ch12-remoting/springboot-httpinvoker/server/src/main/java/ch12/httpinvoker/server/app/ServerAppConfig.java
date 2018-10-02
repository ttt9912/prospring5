package ch12.httpinvoker.server.app;

import ch12.httpinvoker.server.data.DataConfig;
import ch12.httpinvoker.server.service.ServiceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({DataConfig.class, ServiceConfig.class})
public class ServerAppConfig {
}

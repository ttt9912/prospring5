package ch12.httpinvoker.server.app;

import ch12.httpinvoker.server.data.DataConfig;
import ch12.httpinvoker.server.service.ServiceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
// no need to import ConverterConfig because ServiceConfig already does
@Import({DataConfig.class, ServiceConfig.class})
public class ServerAppConfig {
}

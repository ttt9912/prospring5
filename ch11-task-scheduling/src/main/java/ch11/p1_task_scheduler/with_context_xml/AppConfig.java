package ch11.p1_task_scheduler.with_context_xml;

import ch11.config.JpaConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan // detect CarServiceImpl
@Import({JpaConfig.class}) // combine data & task configs
@ImportResource("classpath:task-ch12.server-context.xml")
class AppConfig {
}

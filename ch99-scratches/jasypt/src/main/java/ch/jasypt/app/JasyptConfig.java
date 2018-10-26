package ch.jasypt.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("encrypted.properties")
public class JasyptConfig {
}

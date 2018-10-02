package ch12.httpinvoker.server.service;

import ch12.httpinvoker.server.converter.ConverterConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(ConverterConfig.class)
@ComponentScan
public class ServiceConfig {
}

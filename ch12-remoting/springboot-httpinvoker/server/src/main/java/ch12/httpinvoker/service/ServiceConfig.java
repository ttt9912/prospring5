package ch12.httpinvoker.service;

import ch12.httpinvoker.converter.ConverterConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(ConverterConfig.class)
@ComponentScan
public class ServiceConfig {
}

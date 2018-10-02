package ch12.httpinvoker.server.converter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class ConverterConfig {
    @Bean
    public ConversionServiceFactoryBean conversionService() {
        ConversionServiceFactoryBean conversionServiceFactoryBean =
                new ConversionServiceFactoryBean();

        final Set<Converter> converters = new HashSet<>();
        converters.add(singerConverter());
        converters.add(singerTransformer());
        conversionServiceFactoryBean.setConverters(converters);
        conversionServiceFactoryBean.afterPropertiesSet();
        return conversionServiceFactoryBean;
    }

    @Bean // Entity -> ApiElement
    public SingerConverter singerConverter() {
        return new SingerConverter();
    }

    @Bean // ApiElement -> Entity
    public SingerTransformer singerTransformer() {
        return new SingerTransformer();
    }
}

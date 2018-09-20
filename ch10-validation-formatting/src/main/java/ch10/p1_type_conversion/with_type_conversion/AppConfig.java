package ch10.p1_type_conversion.with_type_conversion;

import ch10.p1_type_conversion.Singer;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.convert.converter.Converter;

import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/*
 * ConversionService: instruct spring to use the type conversion system
 *
 * if no ConversionServiceFactoryBean is configured, spring uses PropertyEditor
 */
@Configuration
@ComponentScan(basePackages = "ch10.p1_type_conversion.with_type_conversion")
@PropertySource("classpath:p1/application.properties")
class AppConfig {

    @Value("${date.format.datePattern}")
    private String dateFormatPattern;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean // values are read from application.properties file
    public Singer john(@Value("${countrySinger.firstName}") String firstName,
                       @Value("${countrySinger.lastName}") String lastName,
                       @Value("${countrySinger.personalSite}") URL personalSite,
                       @Value("${countrySinger.birthDate}") DateTime birthDate) throws Exception {
        Singer singer = new Singer();
        singer.setFirstName(firstName);
        singer.setLastName(lastName);
        singer.setPersonalSite(personalSite);
        singer.setBirthDate(birthDate);
        return singer;
    }

    @Bean
    public ConversionServiceFactoryBean conversionService() {
        ConversionServiceFactoryBean conversionServiceFactoryBean =
                new ConversionServiceFactoryBean();

        final Set<Converter> converters = new HashSet<>();
        converters.add(stringToDateTimeConverter());
        converters.add(singerToAnotherSingerConverter());

        conversionServiceFactoryBean.setConverters(converters);
        conversionServiceFactoryBean.afterPropertiesSet();
        return conversionServiceFactoryBean;
    }

    @Bean
    StringToDateTimeConverter stringToDateTimeConverter() {
        StringToDateTimeConverter conv = new StringToDateTimeConverter();
        conv.setDatePattern(dateFormatPattern);
        conv.init();
        return conv;
    }

    @Bean
    SingerToAnotherSingerConverter singerToAnotherSingerConverter() {
        return new SingerToAnotherSingerConverter();
    }


}

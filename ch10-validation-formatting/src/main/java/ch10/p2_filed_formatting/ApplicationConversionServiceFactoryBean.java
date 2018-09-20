package ch10.p2_filed_formatting;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/*
 * Formatter: String to type and vice-versa
 *
 * parse(): String to T
 * print(): T to formatted String
 */
@Component("conversionService")
class ApplicationConversionServiceFactoryBean extends FormattingConversionServiceFactoryBean {
    private static Logger logger = LoggerFactory.getLogger(ApplicationConversionServiceFactoryBean.class);

    private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    private DateTimeFormatter dtf;
    private String datePattern = DEFAULT_DATE_PATTERN;
    private Set<Formatter<?>> formatters = new HashSet<>();

    @Autowired(required = false)
    public void setDatePattern(String datePattern) {
        this.datePattern = datePattern;
    }

    @PostConstruct
    public void init() {
        dtf = DateTimeFormat.forPattern(datePattern);
        formatters.add(getDateTimeFormatter());
        // set the formatters
        setFormatters(formatters);
    }

    public Formatter<DateTime> getDateTimeFormatter() {
        return new Formatter<DateTime>() {

            @Override
            public DateTime parse(final String dateTimeString, final Locale locale) throws ParseException {
                logger.info("parsing date String: {}", dateTimeString);
                return dtf.parseDateTime(dateTimeString);
            }

            @Override
            public String print(final DateTime dateTime, final Locale locale) {
                logger.info("formatting DateTime: {}", dateTime);
                return dtf.print(dateTime);
            }
        };
    }

    public String getDatePattern() {
        return datePattern;
    }
}

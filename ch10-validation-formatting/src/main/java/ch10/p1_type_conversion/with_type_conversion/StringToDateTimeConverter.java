package ch10.p1_type_conversion.with_type_conversion;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.core.convert.converter.Converter;

import javax.annotation.PostConstruct;

/*
 * create custom converter by implementing Converter<S,T>
 */
class StringToDateTimeConverter implements Converter<String, DateTime> {
    private static final String DEFAULT_PATTERN = "yyyy-MM-dd";
    private DateTimeFormatter dtf;
    private String datePattern = DEFAULT_PATTERN;


    @Override
    public DateTime convert(final String dateString) {
        return dtf.parseDateTime(dateString);
    }

    public String getDatePattern() {
        return datePattern;
    }

    public void setDatePattern(final String datePattern) {
        this.datePattern = datePattern;
    }

    @PostConstruct
    public void init() {
        dtf = DateTimeFormat.forPattern(datePattern);
    }
}

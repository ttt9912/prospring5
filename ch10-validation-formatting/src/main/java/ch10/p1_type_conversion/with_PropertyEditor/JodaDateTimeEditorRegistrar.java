package ch10.p1_type_conversion.with_PropertyEditor;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

import java.beans.PropertyEditorSupport;

/*
 * custom editor for converting String values to Joda DateTime
 */
class JodaDateTimeEditorRegistrar implements PropertyEditorRegistrar {
    private DateTimeFormatter dateTimeFormatter;

    public JodaDateTimeEditorRegistrar(final String pattern) {
        this.dateTimeFormatter = DateTimeFormat.forPattern(pattern);
    }

    @Override
    public void registerCustomEditors(final PropertyEditorRegistry registry) {
        registry.registerCustomEditor(DateTime.class, new JodaDateTimeEditor(dateTimeFormatter));
    }

    // custom editor for converting String values to Joda DateTime
    private static class JodaDateTimeEditor extends PropertyEditorSupport {
        private DateTimeFormatter dateTimeFormatter;

        JodaDateTimeEditor(final DateTimeFormatter dateTimeFormatter) {
            this.dateTimeFormatter = dateTimeFormatter;
        }

        @Override
        public void setAsText(final String text) throws IllegalArgumentException {
            setValue(DateTime.parse(text, dateTimeFormatter));
        }
    }
}

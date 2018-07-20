package ch4.p4_property_editors.built_in;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/*
 * Built-in PropertyEditors
 *
 * Grundsätzlich müssen nur die Setter definiert sein.
 *
 * PropertyEditorRegistrar: nur für Spezialeditoren, resp. Konfiguration der Editoren
 */
public class DataBean {

    private Boolean trueOrFalse;        // CustomBooleanEditor
    private String stringTrimmer;       // StringTrimmerEditor
    private Float aFloat;               // CustomNumberEditor
    private Character character;        // CharacterEditor
    private byte[] bytes;               // ByteArrayPropertyEditor
    private List<String> stringList;    // CustomCollectionEditor
    private Date date;                  // CustomDateEditor
    private File file;                  // FileEditor
    private InputStream inputStream;    // InputStreamEditor
    private Locale locale;              // LocaleEditor
    private Pattern pattern;            // PatternEditor
    private Properties properties;      // PropertiesEditor
    private URL url;                    // URLEditor
    private Class cls;                  // ClassEditor


    /*
     * Registrieren von Spezialeditoren für bestimmte Typen
     * hier für Date (eigener formatter) und String (trimmer)
     */
    public static class CustomPropertyEditorRegistrar implements PropertyEditorRegistrar {

        @Override
        public void registerCustomEditors(final PropertyEditorRegistry registry) {
            final SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

            registry.registerCustomEditor(Date.class, new CustomDateEditor(format, true));
            registry.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        }
    }

    // Setters

    public void setTrueOrFalse(final Boolean trueOrFalse) {
        this.trueOrFalse = trueOrFalse;
    }

    public void setStringTrimmer(final String stringTrimmer) {
        this.stringTrimmer = stringTrimmer;
    }

    public void setaFloat(final Float aFloat) {
        this.aFloat = aFloat;
    }

    public void setCharacter(final Character character) {
        this.character = character;
    }

    public void setBytes(final byte[] bytes) {
        this.bytes = bytes;
    }

    public void setStringList(final List<String> stringList) {
        this.stringList = stringList;
    }

    public void setDate(final Date date) {
        this.date = date;
    }

    public void setFile(final File file) {
        this.file = file;
    }

    public void setInputStream(final InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void setLocale(final Locale locale) {
        this.locale = locale;
    }

    public void setPattern(final Pattern pattern) {
        this.pattern = pattern;
    }

    public void setProperties(final Properties properties) {
        this.properties = properties;
    }

    public void setUrl(final URL url) {
        this.url = url;
    }

    public void setCls(final Class cls) {
        this.cls = cls;
    }

    @Override
    public String toString() {
        return "DataBean{" +
                "trueOrFalse=" + trueOrFalse +
                ", stringTrimmer='" + stringTrimmer + '\'' +
                ", aFloat=" + aFloat +
                ", character=" + character +
                ", bytes=" + Arrays.toString(bytes) +
                ", stringList=" + stringList +
                ", date=" + date +
                ", file=" + file +
                ", inputStream=" + inputStream +
                ", locale=" + locale +
                ", pattern=" + pattern +
                ", properties=" + properties +
                ", url=" + url +
                ", cls=" + cls +
                '}';
    }
}

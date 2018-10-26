package ch.jasypt.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/*
 * retrieve the values from the encrypted.properties
 */
@Service
public class JasyptPropertyService {

    @Value("${encrypted.property}")
    private String property;

    // retrieve decrypted value via @Value
    public String getProperty() {
        return property;
    }

    // retrieve decrypted value via Environment.getProperty()
    public String getPasswordUsingEnvironment(Environment environment) {
        return environment.getProperty("encrypted.property");
    }
}

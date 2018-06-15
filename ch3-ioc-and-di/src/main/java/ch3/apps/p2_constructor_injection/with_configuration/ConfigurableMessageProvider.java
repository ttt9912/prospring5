package ch3.apps.p2_constructor_injection.with_configuration;

import ch3.beans.MessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("provider")
class ConfigurableMessageProvider implements MessageProvider {

    private String message;

    @Autowired // Constructor Injection with @Value
    public ConfigurableMessageProvider(@Value("configurable default message") String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}

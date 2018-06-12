package ch3.apps.p2_constructor_injection.with_context_xml;

import ch3.beans.MessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

class ConfigurableMessageProvider implements MessageProvider {

    private String message;

    // message as constructor arg
    public ConfigurableMessageProvider(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}

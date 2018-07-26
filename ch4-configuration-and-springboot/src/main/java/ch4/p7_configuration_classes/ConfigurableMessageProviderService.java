package ch4.p7_configuration_classes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("messageProviderService")
class ConfigurableMessageProviderService implements MessageProvider {

    private String message;

    public ConfigurableMessageProviderService(@Value("Love on the weekend") final String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}

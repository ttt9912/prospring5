package ch4.p10_JSR330;

import javax.inject.Inject;
import javax.inject.Named;

@Named("messageProvider") // injectable bean (same as @Component)
class ConfigurableMessageProvider implements MessageProvider{

    private String message;


    @Inject // constructor injection
    @Named("message") // inject the value with name "message" assigned
    public ConfigurableMessageProvider(final String message) {
        this.message = message;
    }


    @Override
    public String getMessage() {
        return this.message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }
}

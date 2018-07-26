package ch4.p7_configuration_classes;

class ConfigurableMessageProvider implements MessageProvider {

    private String message = "Default message";

    public ConfigurableMessageProvider() {
    }

    public ConfigurableMessageProvider(final String message) {
        this.message = message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}

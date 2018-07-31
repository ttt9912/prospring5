package ch4.p7_configuration_classes;

class StandardOutMessageRenderer implements MessageRenderer {

    private MessageProvider messageProvider;

    @Override
    public void render() {
        if (messageProvider == null) {
            throw new RuntimeException("messageProviderService must be set");
        }

        System.out.println(messageProvider.getMessage());
    }

    @Override
    public void setMessageProvider(final MessageProvider messageProvider) {
        this.messageProvider = messageProvider;
    }

    @Override
    public MessageProvider getMessageProvider() {
        return this.messageProvider;
    }
}

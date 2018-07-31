package ch4.p10_JSR330;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Named("messageRenderer") // injectable singleton bean
@Singleton
class StandardOutMessageRenderer implements MessageRenderer {

    @Inject // setter injection
    @Named("messageProvider")
    private MessageProvider messageProvider = null;

    @Override
    public void render() {
        if (messageProvider == null) {
            throw new RuntimeException("messageProvider must be set!");
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

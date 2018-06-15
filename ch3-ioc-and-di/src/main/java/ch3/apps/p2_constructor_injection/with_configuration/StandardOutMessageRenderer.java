package ch3.apps.p2_constructor_injection.with_configuration;

import ch3.beans.MessageProvider;
import ch3.beans.MessageRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("renderer")
class StandardOutMessageRenderer implements MessageRenderer {
    private MessageProvider messageProvider;

    @Autowired // setter injection
    public void setMessageProvider(MessageProvider provider) {
        this.messageProvider = provider;
    }

    public void render() {
        if (messageProvider == null) {
            throw new RuntimeException("Must set property msgProvider of class: "
                    + StandardOutMessageRenderer.class.getName());
        }
        System.out.println(messageProvider.getMessage());
    }

    public MessageProvider getMessageProvider() {
        return this.messageProvider;
    }
}

package ch3.apps.p1_setter_injection.with_context_xml;

import ch3.beans.MessageProvider;
import ch3.beans.MessageRenderer;

class StandardOutMessageRenderer implements MessageRenderer{
    private MessageProvider messageProvider;


    public void render() {
        if (messageProvider == null) {
            throw new RuntimeException("Must set property msgProvider of class: "
                    + StandardOutMessageRenderer.class.getName());
        }
        System.out.println(messageProvider.getMessage());
    }

    public void setMessageProvider(MessageProvider provider) {
        this.messageProvider = provider;
    }

    public MessageProvider getMessageProvider() {
        return this.messageProvider;
    }
}

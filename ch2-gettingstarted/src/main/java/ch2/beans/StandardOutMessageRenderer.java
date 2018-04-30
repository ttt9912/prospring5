package ch2.beans;

/**
 * Namensgebung von Variablen und getter/setter muss der JavaBeans definition folgen.
 * Ansonsten kann der context z.B. beim setzten eines properties den setter nicht aufrufen.
 */

public class StandardOutMessageRenderer implements MessageRenderer {
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

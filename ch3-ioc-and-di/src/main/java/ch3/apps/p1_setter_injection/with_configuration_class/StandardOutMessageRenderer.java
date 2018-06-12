package ch3.apps.p1_setter_injection.with_configuration_class;

import ch3.beans.MessageProvider;
import ch3.beans.MessageRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * @Service ist eine Bean Annotation und sagt Spring,
 * dass dieses Bean injectable ist.
 * Service bezeichnet ein Bean mit viel Funktionalität,
 * die ggf. von anderen Beans genutzt werden kann.
 *
 * Alternativ zu @Autowired:
 *  @Resource(name = "provider")
 */

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

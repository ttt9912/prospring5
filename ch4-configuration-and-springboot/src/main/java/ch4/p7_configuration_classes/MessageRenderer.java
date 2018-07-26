package ch4.p7_configuration_classes;

import org.springframework.beans.factory.support.MethodReplacer;

public interface MessageRenderer {

    void render();

    void setMessageProvider(MessageProvider messageProvider);

    MessageProvider getMessageProvider();
}

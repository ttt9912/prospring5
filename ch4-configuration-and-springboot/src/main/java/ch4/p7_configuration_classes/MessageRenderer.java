package ch4.p7_configuration_classes;

interface MessageRenderer {

    void render();

    void setMessageProvider(MessageProvider messageProvider);

    MessageProvider getMessageProvider();
}

package ch4.p10_JSR330;

interface MessageRenderer {

    void render();
    void setMessageProvider(final MessageProvider messageProvider);
    MessageProvider getMessageProvider();
}

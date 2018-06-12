package ch3.apps.p1_setter_injection.with_context_xml;

import ch3.beans.MessageProvider;

class HelloWorldMessageProvider implements MessageProvider{
    public String getMessage() {
        return "Hello World";
    }
}

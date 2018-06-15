package ch3.apps.p1_setter_injection.with_configuration;

import ch3.beans.MessageProvider;
import org.springframework.stereotype.Component;

/*
 * @Component ist eine Bean Annotation und sagt Spring,
 * dass dieses Bean injectable ist.
 */

@Component("provider")
class HelloWorldMessageProvider implements MessageProvider {
    public String getMessage() {
        return "Hello World";
    }
}

package ch3.apps.p5_lookup_method_injection.with_annotations;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

/*
 * is not abstract, compared with the context.xml version
 */

@Component("abstractLookupBean")
class AbstractLookupDemoBean implements DemoBean{

    @Lookup("singer")
    public Singer getMySinger() {
        return null; // overriden dynamically by spring CGLIB
    }

    @Override
    public void doSomething() {
        getMySinger().sing();
    }
}

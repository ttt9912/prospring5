package ch3.apps.p5_lookup_method_injection.with_conext_xml;

/*
 * abstract getMySinger() is called by doSomething() to obtain a Singer instance
 *
 * Spring uses CGLIB to generate a subclass of AbstractLookupDemoBean that
 * overrides the method dynamically
 */

abstract class AbstractLookupDemoBean implements DemoBean {

    public abstract Singer getMySinger();

    @Override
    public void doSomething() {
        getMySinger().sing();
    }
}

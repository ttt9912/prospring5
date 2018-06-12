package ch3.apps.p5_lookup_method_injection.with_conext_xml;

/*
 * uses the stored Singer instance
 */

class StandardLookupDemoBean implements DemoBean {
    private Singer mySinger;

    public StandardLookupDemoBean(Singer mySinger) {
        this.mySinger = mySinger;
    }

    public StandardLookupDemoBean() {
    }

    // setter injection
    public void setMySinger(Singer mySinger) {
        this.mySinger = mySinger;
    }

    @Override
    public Singer getMySinger(){
        return this.mySinger;
    }

    @Override
    public void doSomething() {
        getMySinger().sing();
    }
}

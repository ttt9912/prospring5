package ch2.apps.app_without_spring;

import ch2.beans.HelloWorldMessageProvider;
import ch2.beans.MessageProvider;
import ch2.beans.MessageRenderer;
import ch2.beans.StandardOutMessageRenderer;

class HelloWorldApplication {

    public static void main(String[] args) {
        MessageRenderer mr = new StandardOutMessageRenderer();
        MessageProvider mp = new HelloWorldMessageProvider();
        mr.setMessageProvider(mp);
        mr.render();
    }
}

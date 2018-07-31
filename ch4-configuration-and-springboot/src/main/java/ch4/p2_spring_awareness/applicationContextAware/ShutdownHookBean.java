package ch4.p2_spring_awareness.applicationContextAware;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.GenericXmlApplicationContext;


/*
 * ApplicationContextAware ermöglicht es einem Bean, auf den ApplicationContext zuzugreifen.
 *
 * Für Beans, die andere Beans brauchen, aber diese nicht via Constructor-Injection
 * oder Setter-Injection erhalten.
 *
 * setApplicationContext()
 */
class ShutdownHookBean implements ApplicationContextAware{
    private ApplicationContext ctx;

    @Override
    public void setApplicationContext(final ApplicationContext ctx) throws BeansException {

        System.out.println("ShutdownHookBean: setting ApplicationContext");

        if(ctx instanceof GenericXmlApplicationContext){
            System.out.println("ShutdownHookBean: registering shutdown hook");
            ((GenericXmlApplicationContext) ctx).registerShutdownHook();
        }
    }
}

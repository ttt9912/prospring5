package ch4.p2_spring_awareness;

import ch4.p2_spring_awareness.applicationContextAware.DestructiveBeanWithInterface;
import ch4.p2_spring_awareness.beanNameAware.NamedSinger;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

class Demo {

    /*
     * Shutdown hook: obwohl hier keine calls zu destroy() sind, wird ShutdownHookBean als ein
     * shutdown hook registriert und ruft destroy() auf, wenn die App terminiert.
     */
    @Test
    void applicationContextAware() {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("applicationContextAware_context.xml");
        ctx.registerShutdownHook();
        ctx.refresh();

        ctx.getBean("destructiveBean", DestructiveBeanWithInterface.class);

        ctx.close();
    }


    @Test
    void beanNameAware() {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("beanNameAware_context.xml");
        ctx.refresh();

        NamedSinger bean = (NamedSinger) ctx.getBean("johnMayer");
        bean.sing();

        ctx.close();
    }
}

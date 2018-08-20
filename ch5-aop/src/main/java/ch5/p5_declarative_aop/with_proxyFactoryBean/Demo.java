package ch5.p5_declarative_aop.with_proxyFactoryBean;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

/*
 * Declarative AOP: application code is not touched
 *                  no hardcoded aop functionality
 */
class Demo {

    // --------------------------------------------------------------
    // with context.xml
    // --------------------------------------------------------------

    @Disabled
    @Test
    void proxyFactoryBean_advices_withContextXml() {

        // DOES NOT WORK

        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("proxyFactoryBean_advices_context.xml");
        ctx.refresh();

        Documentarist documentaristOne = ctx.getBean("documentaristOne", Documentarist.class);
        Documentarist documentaristTwo = ctx.getBean("documentaristTwo", Documentarist.class);

        System.out.println("Documentarist One");
        documentaristOne.execute();

        System.out.println("\nDocumentarist Two");
        documentaristTwo.execute();
    }

    @Test
    void proxyFactoryBean_introductions_withContextXml() {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("proxyFactoryBean_introductions_context.xml");
        ctx.refresh();

        Contact proxy = (Contact) ctx.getBean("bean");
        IsModified proxyInterface = (IsModified) proxy;

        System.out.println("has been modified?: " + proxyInterface.isModified());
        proxy.setName("John Mayer");
        System.out.println("has been modified?: " + proxyInterface.isModified());
        proxy.setName("Eric Clapton");
        System.out.println("has been modified?: " + proxyInterface.isModified());
    }


    // --------------------------------------------------------------
    // with Configuration
    // --------------------------------------------------------------

    @Test
    void proxyFactoryBean_introductions_withConfiguration() {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        Contact proxy = (Contact) ctx.getBean("bean");
        IsModified proxyInterface = (IsModified) proxy;

        System.out.println("has been modified?: " + proxyInterface.isModified());
        proxy.setName("John Mayer");
        System.out.println("has been modified?: " + proxyInterface.isModified());
        proxy.setName("Eric Clapton");
        System.out.println("has been modified?: " + proxyInterface.isModified());

        ctx.close();
    }
}

package ch4.p1_bean_lifecycles.with_context_xml;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

/*
 * Hooking into bean creation and destruction
 *  #1: init-method and destroy-method
 *  #2: implementing the InitializingBean and DisposableBean interface
 *  #3: JSR-250 Annotations
 *
 *  Alle drei Ansätze führen zum selben Ergebnis. Grundsätzlich empfiehlt sich die Variante InitializingBean.
 *  Ausser, wenn Portabilität zwischen IoC Containern eine Anforderung ist. Dann besser init-method oder
 *  JSR250 verwenden (denn InitializingBean ist Spring spezifisch).
 *
 *  Destroy auslösen:
 *   #1: ConfigurableListableBeanFactory.destroySingletons(); destroys singleton beans (cleanup)
 *   #2: ctx.registerShutdownHook(); // calls destroySingletons() in a separate thread (mainly used for standalone apps)
 *
 */
class BeanLifecycleDemo {

    @Test
    void withInitializationMethod() {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("initializationMethod_context.xml");
        ctx.refresh();

        ctx.getBean("singerOne");
        ctx.getBean("singerTwo");
        ctx.getBean("singerThree");

        ctx.getBeanFactory().destroySingletons();
        ctx.close();
    }

    @Test
    void withInitializingBeanInterface() {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("initializingBeanInterface_context.xml");
        ctx.refresh();

        ctx.getBean("singerOne");
        ctx.getBean("singerTwo");
        ctx.getBean("singerThree");

        ctx.getBeanFactory().destroySingletons();

        ctx.close();
    }

    @Test
    void withJSR250Annotations() {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("initializationWithJSR250Annotations_context.xml");
        ctx.refresh();

        ctx.getBean("singerOne");
        ctx.getBean("singerTwo");
        ctx.getBean("singerThree");

        ctx.getBeanFactory().destroySingletons();

        ctx.close();
    }
}

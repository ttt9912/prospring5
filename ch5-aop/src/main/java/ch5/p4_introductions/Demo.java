package ch5.p4_introductions;


import org.junit.jupiter.api.Test;
import org.springframework.aop.IntroductionAdvisor;
import org.springframework.aop.framework.ProxyFactory;

/*
 * Advice: per-class lifecycle (has no state)
 *
 * Introduction: per-instance lifecycle (has state)
 *
 * Introductions: introduce new functionality to an existing object dynamically.
 * Spring can introduce an implementation of any Interface to an existing object.
 */
class Demo {

    @Test
    void introduction_demo() {
        Contact target = new Contact();
        target.setName("John Mayer");

        IntroductionAdvisor advisor = new IsModifiedAdvisor();

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvisor(advisor);
        proxyFactory.setOptimize(true); // use CGLIB Proxy

        Contact proxy = (Contact) proxyFactory.getProxy();
        IsModified proxyInterface = (IsModified) proxy;

        System.out.println("has been modified?: " + proxyInterface.isModified());
        proxy.setName("John Mayer");
        System.out.println("has been modified?: " + proxyInterface.isModified());
        proxy.setName("Eric Clapton");
        System.out.println("has been modified?: " + proxyInterface.isModified());
    }
}

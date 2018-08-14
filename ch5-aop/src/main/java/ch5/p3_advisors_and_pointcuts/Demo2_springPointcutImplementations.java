package ch5.p3_advisors_and_pointcuts;

import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;

/*
 * Spring provides 8 Pointcut implementations.
 *  - two abstract classes for static and dynamic pointcut creation
 *  - six concrete classes (can be used directly, no new implementation required)
 */
class Demo2_springPointcutImplementations {

    // --------------------------------------------------------------
    // Pointcut implementations without own Pointcut class
    // --------------------------------------------------------------

    @Test
    void name_matching() {
        // NameMatchMethodPointcut: matching against a list of method names

        GrammyGuitarist grammyGuitarist = new GrammyGuitarist();

        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.addMethodName("sing");
        pointcut.addMethodName("rest");
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, new SimpleAdvice());

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(grammyGuitarist);
        proxyFactory.addAdvisor(advisor);

        GrammyGuitarist proxy = (GrammyGuitarist) proxyFactory.getProxy();
        proxy.sing();               // should be advised
        proxy.sing(new Guitar());   // should be advised
        proxy.rest();               // should be advised
        proxy.talk();               // should not be advised
    }

    @Test
    void regex() {
        // JdkRegexpMethodPointcut: matching method names against regex pattern

        Guitarist guitarist = new Guitarist();

        JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
        pointcut.setPattern(".*sing.*");
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, new SimpleAdvice());

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(guitarist);
        proxyFactory.addAdvisor(advisor);

        Guitarist proxy = (Guitarist) proxyFactory.getProxy();

        proxy.sing();   // should be advised
        proxy.sing2();  // should be advised
        proxy.rest();   // should not be advised
    }

    @Test
    void annotation_matching_pointcuts() {
        // AnnotationMatchingPointcut: looks for a specific Java annotation on a class or method

        GuitarHero guitarHero = new GuitarHero();

        AnnotationMatchingPointcut pointcut =
                AnnotationMatchingPointcut.forMethodAnnotation(SimpleAdviceRequired.class);

        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, new SimpleAdvice());

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(guitarHero);
        proxyFactory.addAdvisor(advisor);

        GuitarHero proxy = (GuitarHero) proxyFactory.getProxy();

        proxy.sing(new Guitar());   // should be advised
        proxy.rest();               // should not be advised
    }


    // --------------------------------------------------------------
    // Convenience Advisor that acts as both Advisor and Pointcut
    // --------------------------------------------------------------

    @Test
    void convenience_advisor() {
        // Pointcut details are configured on NameMatchMethodPointcutAdvisor
        // instead of NameMatchMethodPointcut.

        GrammyGuitarist grammyGuitarist = new GrammyGuitarist();

        NameMatchMethodPointcutAdvisor pointcutAdvisor = new NameMatchMethodPointcutAdvisor(new SimpleAdvice());
        pointcutAdvisor.addMethodName("sing");
        pointcutAdvisor.addMethodName("rest");

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(grammyGuitarist);
        proxyFactory.addAdvisor(pointcutAdvisor);

        GrammyGuitarist proxy = (GrammyGuitarist) proxyFactory.getProxy();
        proxy.sing();
        proxy.sing(new Guitar());
        proxy.rest();
        proxy.talk();
    }
}

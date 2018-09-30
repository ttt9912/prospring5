package ch5.p3_advisors_and_pointcuts;

import org.aopalliance.aop.Advice;
import org.junit.Test;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/*
 * Static Pointcut: checks methods to be advised only once at beginning (static)
 *
 * Dynamic Pointcut: checks methods to be advised at beginning (static)
 * and for each invocation (dynamic)
 */
class Demo1_Base {


    // --------------------------------------------------------------
    // Bases for static and dynamic Pointcut implementations
    // --------------------------------------------------------------

    @Test
    void static_pointcut() {
        // StaticMethodMatcherPointcut: base for building static Pointcuts

        GoodGuitarist goodGuitarist = new GoodGuitarist();
        GreatGuitarist greatGuitarist = new GreatGuitarist();

        Pointcut pointcut = new SimpleStaticPointcut();
        Advice advice = new SimpleAdvice();
        Advisor advisor = new DefaultPointcutAdvisor(pointcut, advice);

        ProxyFactory proxyFactory1 = new ProxyFactory();
        proxyFactory1.addAdvisor(advisor);
        proxyFactory1.setTarget(goodGuitarist);
        GoodGuitarist goodGuitaristProxy = (GoodGuitarist) proxyFactory1.getProxy();

        ProxyFactory proxyFactory2 = new ProxyFactory();
        proxyFactory2.addAdvisor(advisor);
        proxyFactory2.setTarget(greatGuitarist);
        GreatGuitarist greatGuitaristProxy = (GreatGuitarist) proxyFactory2.getProxy();

        goodGuitaristProxy.sing();  // should be advised
        greatGuitaristProxy.sing(); // should not be advised
    }

    @Test
    void dynamic_pointcut() {
        // DynamicMethodMatcherPointcut: base for building dynamic Pointcuts

        SampleBean target = new SampleBean();

        DefaultPointcutAdvisor advisor =
                new DefaultPointcutAdvisor(new SimpleDynamicPointcut(), new SimpleAdvice());

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvisor(advisor);
        SampleBean proxy = (SampleBean) proxyFactory.getProxy();

        proxy.foo(1);    // should be advised
        proxy.foo(100);  // should not be advised
        proxy.bar();        // should not be advised
    }
}

package ch5.p3_advisors_and_pointcuts;

import org.junit.Test;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ControlFlowPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/*
 * Control Flow Pointcuts: advise a method only, if it is called from another specific method.
 */
class Demo3_controlFlowPointcuts {

    @Test
    void control_flow_pointcuts() {
        // advice wird nur auf target Methoden ausgeführt,
        // die von Demo3_controlFlowPointcuts.test() aus aufgerufen werden.

        TestBean target = new TestBean();

        Pointcut pointcut = new ControlFlowPointcut(Demo3_controlFlowPointcuts.class, "test");
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, new SimpleAdvice());

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvisor(advisor);

        TestBean proxy = (TestBean) proxyFactory.getProxy();

        System.out.println("--- trying normal invoke: ---");
        proxy.foo(); // not advised

        System.out.println("--- trying under test(): ---");
        test(proxy);
    }

    private void test(final TestBean proxy) {
        proxy.foo(); // advised
    }


}

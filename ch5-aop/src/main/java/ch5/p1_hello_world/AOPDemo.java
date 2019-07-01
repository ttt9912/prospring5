package ch5.p1_hello_world;

import org.junit.Test;
import org.springframework.aop.framework.ProxyFactory;

public class AOPDemo {

    @Test
    public void helloWorld() {

        Agent target = new Agent();

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvice(new AgentDecorator());
        proxyFactory.setTarget(target);

        Agent proxy = (Agent) proxyFactory.getProxy();

        target.speak(); // Bond
        System.out.println();
        proxy.speak(); // James Bond!
    }
}

package p2_advices;

import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

class Demo {

    @Test
    void before_advice() {
        Guitarist guitarist = new Guitarist();

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvice(new SimpleBeforeAdvice());
        proxyFactory.setTarget(guitarist);

        Guitarist proxy = (Guitarist) proxyFactory.getProxy();
        proxy.sing();
    }
}

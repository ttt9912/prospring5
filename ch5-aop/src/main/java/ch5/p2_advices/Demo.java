package ch5.p2_advices;

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

    @Test
    void after_returning_advice() {
        Guitarist guitarist = new Guitarist();

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvice(new SimpleAfterReturningAdvice());
        proxyFactory.setTarget(guitarist);

        Guitarist proxy = (Guitarist) proxyFactory.getProxy();
        proxy.sing();
    }

    @Test
    void around_advice() {
        Guitarist guitarist = new Guitarist();

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvice(new SimpleAroundAdvice());
        proxyFactory.setTarget(guitarist);

        Guitarist proxy = (Guitarist) proxyFactory.getProxy();
        proxy.sing();
    }

    @Test
    void throws_advice() {
        ErrorBean errorBean = new ErrorBean();

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvice(new SimpleThrowsAdvice());
        proxyFactory.setTarget(errorBean);

        ErrorBean proxy = (ErrorBean) proxyFactory.getProxy();

        try {
            proxy.errorProneMethod();
        } catch (Exception ignored) {
        }

        try {
            proxy.otherErrorProneMethod();
        } catch (Exception ignored) {
        }
    }
}

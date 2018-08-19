package ch5.p5_declarative_aop.with_proxyFactoryBean;


import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/*
 * JoinPoint: AspectJ
 */
class AuditAdvice implements MethodBeforeAdvice {

    // TODO: extends MethodBeforeAdvice instead of AspectJ JoinPoint? (p.278)

    @Override
    public void before(final Method method, final Object[] args, final Object target) throws Throwable {
        System.out.println("Executing: " + method.getName());
    }
}

package ch5.p5_declarative_aop.with_aopNamespace;

import org.aspectj.lang.JoinPoint;

/*
 * advice class is required to implement MethodBeforeAdvice
 *
 * JoinPoint argument: is optional. If present, Spring will automatically
 * pass the joinpoint into the method.
 */
class SimpleAdvice {

    public void simpleBeforeAdvice(JoinPoint joinpoint) {
        System.out.println("Executing: " +
                joinpoint.getSignature().getDeclaringTypeName() + " " +
                joinpoint.getSignature().getName());
    }
}

package ch5.p5_declarative_aop.with_aopNamespace;

import org.aspectj.lang.JoinPoint;

/*
 * JoinPoint: optional. Automatically passed in by spring.
 */

class SimpleAdvice {

    public void simpleBeforeAdvice(JoinPoint joinPoint) {
        System.out.println("Executing " +
                joinPoint.getSignature().getDeclaringTypeName() + " " +
                joinPoint.getSignature().getName());
    }

}

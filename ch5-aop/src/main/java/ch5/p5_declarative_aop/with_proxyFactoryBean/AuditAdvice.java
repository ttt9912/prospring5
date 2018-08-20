package ch5.p5_declarative_aop.with_proxyFactoryBean;


import org.aspectj.lang.JoinPoint;

/*
 * JoinPoint: optional. Automatically passed in by spring
 */
class AuditAdvice {

    public void simpleBeforeAdvice(JoinPoint joinPoint) {
        System.out.println("Executing arg");
    }

}

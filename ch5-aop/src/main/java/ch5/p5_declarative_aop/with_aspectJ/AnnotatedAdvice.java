package ch5.p5_declarative_aop.with_aspectJ;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
class AnnotatedAdvice {

    @Pointcut("execution(* ch5.p5_declarative_aop.with_aspectJ..sing*(ch5.p5_declarative_aop.with_aspectJ.Guitar))" +
            "&& args(value)")
    public void singExecution(Guitar value) {
    }

    @Pointcut("bean(john*)")
    public void isJohn() {
    }

    @Before("singExecution(value) && isJohn()")
    public void simpleBeforeAdvice(JoinPoint joinPoint, Guitar value) {
        if (value.getBrand().equals("Gibson")) {
            System.out.println("Executing " +
                    joinPoint.getSignature().getDeclaringTypeName() + " " +
                    joinPoint.getSignature().getName());
        }
    }

    @Around("singExecution(value) && isJohn()")
    public Object simpleAroundAdvice(ProceedingJoinPoint joinPoint, Guitar value) throws Throwable {
        System.out.println("Before Execution: " +
                joinPoint.getSignature().getDeclaringTypeName() + " " +
                joinPoint.getSignature().getName() +
                " argument: " + value.getBrand());

        Object returnValue = joinPoint.proceed();

        System.out.println("After Execution: " +
                joinPoint.getSignature().getDeclaringTypeName() + " " +
                joinPoint.getSignature().getName() +
                " argument: " + value.getBrand() +
                ", return value: " + returnValue);

        return returnValue;
    }
}

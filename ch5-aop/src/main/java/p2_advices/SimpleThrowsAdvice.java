package p2_advices;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

/*
 * Throws Advice executes only when the method throws an exception.
 * Control over program execution similar to after returning advice.
 * Can't return a value instead of throwing an Exception.
 * Limited to change the type of Exception that is thrown.
 *
 * ThrowsAdvice: defines no methods. afterThrowing() is called by Spring.
 */
public class SimpleThrowsAdvice implements ThrowsAdvice {

    // one-arg for all Exception types
    public void afterThrowing(Exception ex) throws Throwable {
        System.out.println("---------------------------------------------------------------------");
        System.out.println("[SimpleThrowsAdvice] Generic Exception capture");
        System.out.println("[SimpleThrowsAdvice] Caught: " + ex.getClass().getName());
        System.out.println("---------------------------------------------------------------------");
    }

    // four-arg for IllegalArgumentExceptions
    public void afterThrowing(Method method, Object args, Object target, IllegalArgumentException ex) throws Throwable {
        System.out.println("---------------------------------------------------------------------");
        System.out.println("[SimpleThrowsAdvice] IllegalArgumentException capture");
        System.out.println("[SimpleThrowsAdvice] Caught: " + ex.getClass().getName());
        System.out.println("[SimpleThrowsAdvice] Method: " + method.getName());
        System.out.println("---------------------------------------------------------------------");
    }
}

package p2_advices;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/*
 * After Returning Advice can't modify the methods arguments, return value
 * and neither prevent the method from executing. This advice is limited to
 * adding processing and throwing an Exception instead of returning a value.
 *
 * AfterReturningAdvice: defines afterReturning() method
 */
public class SimpleAfterReturningAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(final Object o, final Method method, final Object[] objects, final Object o1) throws Throwable {

        System.out.println("[SimpleAfterReturningAdvice] After '" + method.getName() + "' put down guitar.");
    }
}

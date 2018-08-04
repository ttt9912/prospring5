package ch5.p2_advices;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;
import java.util.Arrays;

/*
 * Around Advice is a combination of before and after advice.
 * Difference: return value van be modified.
 *
 * MethodInterceptor: defines invoke() method
 *
 * MethodInvocation: has access to Method, method arguments, return value
 */
class SimpleAroundAdvice implements MethodInterceptor {

    @Override
    public Object invoke(final MethodInvocation methodInvocation) throws Throwable {

        // before advice
        StopWatch stopWatch = new StopWatch();
        stopWatch.start(methodInvocation.getMethod().getName());
        System.out.println("[SimpleAroundAdvice] >> Stopwatch started.");

        // method execution
        Object returnVal = methodInvocation.proceed();

        // after advice
        stopWatch.stop();
        System.out.println("[SimpleAroundAdvice] >> Stopwatch stopped.");
        printInfo(methodInvocation, stopWatch.getTotalTimeMillis());

        return returnVal;
    }

    private void printInfo(final MethodInvocation invocation, final long totalTimeMillis) {
        Method method = invocation.getMethod();
        Object target = invocation.getThis();
        Object[] arguments = invocation.getArguments();

        System.out.println("[SimpleAroundAdvice] >> Executed Method: " + method);
        System.out.println("[SimpleAroundAdvice] >> On object of type: " + target.getClass().getName());
        System.out.println("[SimpleAroundAdvice] >> With arguments: " + Arrays.toString(arguments));
        System.out.println("[SimpleAroundAdvice] >> Took time ms: " + totalTimeMillis);
    }
}

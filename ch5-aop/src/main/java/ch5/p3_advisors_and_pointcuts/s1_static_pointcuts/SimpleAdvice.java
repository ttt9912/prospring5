package ch5.p3_advisors_and_pointcuts.s1_static_pointcuts;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

class SimpleAdvice implements MethodInterceptor {

    @Override
    public Object invoke(final MethodInvocation invocation) throws Throwable {

        System.out.println("[SimpleAdvice] >> invoking " + invocation.getMethod().getName());

        Object returnVal = invocation.proceed();

        System.out.println("[SimpleAdvice] >> done");
        System.out.println();

        return returnVal;
    }
}

package p1_hello_world;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

class AgentDecorator implements MethodInterceptor {

    @Override
    public Object invoke(final MethodInvocation methodInvocation) throws Throwable {

        // before advice
        System.out.print("James ");

        // joinpoint
        Object retVal = methodInvocation.proceed();

        // after advice
        System.out.print("!");

        return retVal;
    }
}

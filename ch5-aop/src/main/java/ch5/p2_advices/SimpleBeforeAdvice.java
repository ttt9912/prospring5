package ch5.p2_advices;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/*
 * Before Advice can modify the methods arguments or prevent the method
 * from executing by throwing an exception.
 *
 * MedhodBeforeAdvice: defines before() method
 */
class SimpleBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(final Method method, final Object[] objects, final Object o) {

        System.out.println("[SimpleBeforeAdvice] >> Before '" + method.getName() + "', tune guitar.");
    }
}

package ch5.p3_advisors_and_pointcuts.s1_static_pointcuts;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;

import java.lang.reflect.Method;

/*
 * static check: executed only once
 * dynamic check: executed for each invoke of target method
 */
class SimpleDynamicPointcut extends DynamicMethodMatcherPointcut {


    @Override // static check
    public boolean matches(final Method method, final Class<?> targetClass) {
        System.out.println("[SimpleDynamicPointcut] static check for " + method.getName());
        return "foo".equals(method.getName());
    }

    @Override // dynamic check
    public boolean matches(final Method method, final Class<?> targetClass, final Object... args) {
        System.out.println("[SimpleDynamicPointcut] dynamic check for " + method.getName());
        return (Integer) args[0] != 100;
    }

    @Override
    public ClassFilter getClassFilter() {
        return clazz -> clazz == SampleBean.class;
    }
}

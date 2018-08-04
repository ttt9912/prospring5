package ch5.p3_advisors_and_pointcuts.s1_static_pointcuts;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

/*
 * Static Pointcut: Spring calls the matches() method once for every method on the target,
 * caching the return value of matches() for each target method (which says, if it should be advised or not).
 */
class SimpleStaticPointcut extends StaticMethodMatcherPointcut {

    @Override
    public boolean matches(final Method method, final Class<?> targetClass) {

        // methods to be advised
        return "sing".equals(method.getName());
    }

    @Override
    public ClassFilter getClassFilter() {

        // types to be advised
        return clazz -> clazz == GoodGuitarist.class;
    }
}

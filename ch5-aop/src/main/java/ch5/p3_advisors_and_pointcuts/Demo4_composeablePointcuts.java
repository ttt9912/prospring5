package ch5.p3_advisors_and_pointcuts;

import org.junit.jupiter.api.Test;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcher;

import java.lang.reflect.Method;

/*
 * Composable Pointcut: einzelner Pointcut, der mehrere MethodMatchers und ClassFilters kombiniert.
 *                      union(): OR, intersection(): AND
 */
class Demo4_composeablePointcuts {

    @Test
    void non_composed() {
        // matches methods starting with "si" (SingMethodMatcher)

        GrammyGuitarist target = new GrammyGuitarist();

        ComposablePointcut pointcut =
                new ComposablePointcut(ClassFilter.TRUE, new SingMethodMatcher());

        GrammyGuitarist proxy = getProxy(pointcut, target);
        proxy.sing(); // advised
        proxy.sing(new Guitar()); // advised
        proxy.talk(); // not advised
        proxy.rest(); // not advised
    }

    @Test
    void union() {
        // matches methods starting with "si" (SingMethodMatcher) OR with name "talk" (TalkMethodMatcher)

        GrammyGuitarist target = new GrammyGuitarist();

        ComposablePointcut pointcut =
                new ComposablePointcut(ClassFilter.TRUE, new SingMethodMatcher());

        pointcut.union(new TalkMethodMatcher());

        GrammyGuitarist proxy = getProxy(pointcut, target);
        proxy.sing(); // advised
        proxy.sing(new Guitar()); // advised
        proxy.talk(); // advised
        proxy.rest(); // not advised
    }

    @Test
    void intersection() {
        // matches methods starting with "si" (SingMethodMatcher) AND end with "st" (RestMethodMatcher)

        GrammyGuitarist target = new GrammyGuitarist();

        ComposablePointcut pointcut =
                new ComposablePointcut(ClassFilter.TRUE, new SingMethodMatcher());

        pointcut.intersection(new RestMethodMatcher());

        GrammyGuitarist proxy = getProxy(pointcut, target);
        proxy.sing(); // not advised
        proxy.sing(new Guitar()); // not advised
        proxy.talk(); // not advised
        proxy.rest(); // not advised
    }

    private GrammyGuitarist getProxy(final ComposablePointcut pointcut, final GrammyGuitarist target) {
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, new SimpleAdvice());
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvisor(advisor);
        return (GrammyGuitarist) proxyFactory.getProxy();
    }


    // matches methods, starting with "si"
    class SingMethodMatcher extends StaticMethodMatcher {
        @Override
        public boolean matches(final Method method, final Class<?> targetClass) {
            return method.getName().startsWith("si");
        }
    }

    // matches methods with name "talk"
    private class TalkMethodMatcher extends StaticMethodMatcher {
        @Override
        public boolean matches(final Method method, final Class<?> targetClass) {
            return method.getName().equals("talk");
        }
    }

    // matches methods, ending with "st"
    private class RestMethodMatcher extends StaticMethodMatcher {
        @Override
        public boolean matches(final Method method, final Class<?> targetClass) {
            return method.getName().endsWith("st");
        }
    }
}



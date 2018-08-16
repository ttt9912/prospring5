package ch5.p4_introductions;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/*
 * Mixin: code that is introduced to the objects.
 * Implements the interface (IsModified) to be introduced.
 *
 * DelegatingIntroductionInterceptor: creates a mixin
 */
class IsModifiedMixin extends DelegatingIntroductionInterceptor implements IsModified {

    private boolean isModified = false;

    // target methods are cached for quicker future retrieval (optional)
    private final Map<Method, Method> methodCache = new HashMap<>();

    @Override
    public boolean isModified() {
        return isModified;
    }

    @Override // optional
    public Object invoke(final MethodInvocation invocation) throws Throwable {
        /*
         * überprüft bei einem Methodenaufruf auf dem target, falls es sich bei der
         * Methode um einen setter handelt, ob der Wert verändert wurde und setzt isModified.
         */

        if (!isModified) {
            if (invocation.getMethod().getName().startsWith("set")
                    && invocation.getArguments().length == 1) {

                Method getter = getGetter(invocation.getMethod());

                if (getter != null) {
                    Object newVal = invocation.getArguments()[0];
                    Object oldVal = getter.invoke(invocation.getThis());

                    if (newVal == null && oldVal == null) {
                        isModified = false;
                    } else if (newVal == null) {
                        isModified = true;
                    } else {
                        isModified = !newVal.equals(oldVal);
                    }
                }
            }
        }

        return super.invoke(invocation);
    }

    private Method getGetter(final Method setter) {
        Method getter = methodCache.get(setter);

        if (getter != null) {
            return getter;
        }

        String getterName = setter.getName().replaceFirst("set", "get");

        try {
            getter = setter.getDeclaringClass().getMethod(getterName);

            synchronized (methodCache) {
                methodCache.put(setter, getter);
            }

            return getter;

        } catch (NoSuchMethodException e) {
            return null;
        }
    }

}

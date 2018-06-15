package ch3.apps.p6_method_replacement;

import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;
import java.util.Arrays;

class FormatMessageReplacer implements MethodReplacer {
    @Override
    public Object reimplement(Object o, Method method, Object[] objects) throws Throwable {

        if (isFormatMessageMethod(method)) {
            // replaces logic for ReplacementTarget.formatMessage()
            String msg = (String) objects[0];
            return "<h3>" + msg + "</h3>";
        } else {
            throw new IllegalArgumentException("Unable to reimplement method " + method.getName());
        }
    }

    private boolean isFormatMessageMethod(Method method) {
        if (method.getParameterTypes().length != 1)
            return false;
        if (!method.getName().equals("formatMessage"))
            return false;
        if (method.getReturnType() != String.class)
            return false;
        if (method.getParameterTypes()[0] != String.class)
            return false;
        return true;
    }
}

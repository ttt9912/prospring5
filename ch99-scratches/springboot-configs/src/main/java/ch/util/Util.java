package ch.util;

import org.springframework.context.ApplicationContext;

public class Util {

    public static void printBeansOfType(final Class<?> clazz, final ApplicationContext ctx) {
        System.out.printf("\n--- registered beans of type %s ---\n", clazz.getSimpleName());
        ctx.getBeansOfType(clazz)
                .forEach((beanname, bean) ->
                        System.out.println("Name: " + beanname
                                + " Object: " + bean));
    }
}

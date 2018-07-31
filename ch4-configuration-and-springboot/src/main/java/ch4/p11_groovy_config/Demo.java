package ch4.p11_groovy_config;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.GenericGroovyApplicationContext;

// Groovy Libary must be added: https://www.jetbrains.com/help/idea/getting-started-with-groovy.html
class Demo {

    /*
     * configure bean definitions and ApplicationContext using Groovy
     *
     * GenericGroovyApplicationContext: create ApplicationContext from Groovy Script (ch4.p11_groovy_config.beansaa.groovyvy)
     *
     */
    @Test
    void groovy() {
        GenericGroovyApplicationContext ctx = new GenericGroovyApplicationContext("bean_def.groovy");

        final Employee employee = ctx.getBean("employee", Employee.class);
        System.out.println(employee);
    }
}

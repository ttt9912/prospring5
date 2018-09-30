package ch4.p11_groovy_config;

import org.junit.Test;
import org.springframework.context.support.GenericGroovyApplicationContext;

class Demo {

    /*
     * configure bean definitions and ApplicationContext using Groovy
     *
     * GenericGroovyApplicationContext: create ApplicationContext from Groovy Script (ch4.p11_groovy_config.beansaa.groovyvy)
     */
    @Test
    void groovy() {
        GenericGroovyApplicationContext ctx = new GenericGroovyApplicationContext("bean_definition.groovy");

        final Employee employee = ctx.getBean("employee", Employee.class);
        System.out.println(employee);
    }
}

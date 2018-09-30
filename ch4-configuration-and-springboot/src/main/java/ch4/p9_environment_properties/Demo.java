package ch4.p9_environment_properties;


import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

import java.util.HashMap;
import java.util.Map;

/*
 * Environment: all system properties, environment variables and
 * application properties of the running ch12.server
 *
 * PropertySource: store the applications environment configuration
 */
class Demo {

    @Test
    void environment_properties_addFirst() {

        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.refresh();

        ConfigurableEnvironment env = ctx.getEnvironment();
        MutablePropertySources propertySources = env.getPropertySources();

        // Properties in JVMs System class
        System.out.println("System[user.home]: " + System.getProperty("user.home"));
        // Properties in Environment class
        System.out.println("Environment[user.home]: " + env.getProperty("user.home"));


        // add new Property to Environment
        Map<String, Object> appMap = new HashMap<>();
        appMap.put("user.home", "application_home");

        // addFirst does override existing values, addLast doesn't
        propertySources.addFirst(new MapPropertySource("prospring5_MAP", appMap));

        System.out.println("Environment[user.home]: " + env.getProperty("user.home"));


        ctx.close();
    }
}

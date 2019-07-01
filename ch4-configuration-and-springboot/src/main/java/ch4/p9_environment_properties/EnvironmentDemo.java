package ch4.p9_environment_properties;


import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

import java.util.HashMap;
import java.util.Map;

/*
 * Environment: all system properties, environment variables and
 * application properties of the running server
 *
 * PropertySource: store the applications environment configuration
 */
public class EnvironmentDemo {

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.refresh();

        ConfigurableEnvironment env = ctx.getEnvironment();

        // Properties in JVMs System class
        System.out.println("System[user.home]: " + System.getProperty("user.home"));
        // Properties in Environment class
        System.out.println("Environment[user.home]: " + env.getProperty("user.home"));

        // add new Property to Environment (create new PropertySource)
        Map<String, Object> appMap = new HashMap<>();
        appMap.put("user.home", "application_home");
        MutablePropertySources propertySources = env.getPropertySources();
        propertySources.addFirst(new MapPropertySource("prospring5_MAP", appMap));

        System.out.println("Environment[user.home]: " + env.getProperty("user.home"));


        ctx.close();
    }
}

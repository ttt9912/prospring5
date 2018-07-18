package ch4.p2_spring_awareness.beanNameAware;

import org.springframework.beans.factory.BeanNameAware;

/*
 * setBeanName() wird von Spring mit dem in context.xml definierten namen/id aufgerufen.
 *
 * Der Aufruf ist nach der Konfiguration des Beans, aber vor allen lifecycle Methoden.
 */
public class NamedSinger implements BeanNameAware {
    private String name;

    // called automatically by spring during bean creation.
    @Override
    public void setBeanName(final String beanName) {
        this.name = beanName;
    }

    public void sing() {
        System.out.println("My Bean Name is: " + name);
    }
}

package ch3.apps.p8_bean_dependencies;

import org.springframework.context.support.GenericXmlApplicationContext;

/*
 * Versichern, dass Beans in der richtigen Reihenfolge instanziiert werden:
 * - depends-on Attribut im context
 * - abhängige Klasse (Singer) implementiert ApplicationContextAware
 */

class Demo {
    public static void main(String[] args){
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("beanDependencies_context.xml");
        ctx.refresh();

        Singer johnMayer = ctx.getBean("johnMayer", Singer.class);
        johnMayer.sing();

        ctx.close();
    }
}

/*
 *  Configuration Variante:
 *  - Guitar und Singer mit @Component annotieren
 *  - depends-on Attribut wird durch @DependsOn("fender") auf Ebene @Component ersetzt
 *  - abhängige Klasse (Singer) implementiert nach wie vor ApplicationContextAware
 */
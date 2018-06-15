package ch3.apps.p9_autowiring.with_context_xml.simple_beans;

import org.springframework.context.support.GenericXmlApplicationContext;

class Demo {
    public static void main(String[] args){
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("autowiring_context.xml");
        ctx.refresh();

        // Autowiring byName instanziiert properties, für die es gleichnamige Beans gibt
        System.out.println("\nAutowiring mode = byName");
        Target targetByName = ctx.getBean("targetByName", Target.class);
        System.out.println(targetByName);


        // Autowiring byType instanziiert properties, für die es Beans des gleichen Typs gibt
        System.out.println("\nAutowiring mode = byType");
        Target targetByType = ctx.getBean("targetByType", Target.class);
        System.out.println(targetByType);


        // Autowiring constructor ruft den all args constructor auf
        System.out.println("\nAutowiring mode = constructor");
        Target targetConstructor = ctx.getBean("targetConstructor", Target.class);
        System.out.println(targetConstructor);

        ctx.close();
    }
}

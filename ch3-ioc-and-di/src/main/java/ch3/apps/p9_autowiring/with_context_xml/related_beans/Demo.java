package ch3.apps.p9_autowiring.with_context_xml.related_beans;

import org.springframework.context.support.GenericXmlApplicationContext;

/*
 * Autowiring von Verwandten Beans
 * - 2 Verwandte Beans: primary Attribut
 */
class Demo {
    public static void main(String[] args){
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("autowiring_2_context.xml");
        ctx.refresh();

        System.out.println("\nAutowiring mode = byType");
        Target targetByType = ctx.getBean("targetByType", Target.class);
        System.out.println(targetByType);

        ctx.close();
    }
}

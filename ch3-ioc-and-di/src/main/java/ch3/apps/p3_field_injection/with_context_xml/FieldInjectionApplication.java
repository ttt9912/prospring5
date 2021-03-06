package ch3.apps.p3_field_injection.with_context_xml;

import org.springframework.context.support.GenericXmlApplicationContext;

/*
 * Field Injection: dependency wird direkt ins Field injected (@Autowired).
 * Kein Konstruktor oder Setter ist mehr nötig.
 *
 */

class FieldInjectionApplication {

    public static void main(String... args) {

        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("fieldInjection_context.xml");
        ctx.refresh();

        Singer singerBean = ctx.getBean(Singer.class);
        singerBean.sing();
        ctx.close();
    }
}

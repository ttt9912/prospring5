package ch3.apps.p7_bean_naming.with_context_xml;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Map;

class BeanNaming {

    /*
     * Es werden 5 Beans des Typs String (=> aliases) mit unterschiedlichem
     * Bean name geladen.
     */
    public static void main(String[] args){
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("beanNaming_context.xml");
        ctx.refresh();

        Map<String, String> beans = ctx.getBeansOfType(String.class);
        beans.forEach((key, value) -> System.out.println(key));

        ctx.close();
    }
}

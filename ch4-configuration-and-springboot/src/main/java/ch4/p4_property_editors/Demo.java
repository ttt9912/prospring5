package ch4.p4_property_editors;

import ch4.p4_property_editors.built_in.DataBean;
import ch4.p4_property_editors.custom.Person;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Demo {

    /*
     * PropertyEditors: injecten von property values als String
     * PropertyEditor übernimmt die konvertierung von String zum Typ des Properties
     */
    @Test
    void builtIn_propertyEditors() {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("propertyEditorBeans_context.xml");
        ctx.refresh();

        DataBean builtInSample = (DataBean) ctx.getBean("builtInSample");
        System.out.println(builtInSample);

        ctx.close();
    }

    @Test
    void custom_propertyEditors() {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("customPropertyEditorBeans_context.xml");
        ctx.refresh();

        Person person = (Person) ctx.getBean("person");
        System.out.println(person);

        ctx.close();
    }
}

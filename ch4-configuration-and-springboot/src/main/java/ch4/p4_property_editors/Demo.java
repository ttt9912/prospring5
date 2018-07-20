package ch4.p4_property_editors;

import ch4.p4_property_editors.built_in.DataBean;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

/*
 * PropertyEditors: injecten von property values als String
 * PropertyEditor übernimmt die konvertierung von String zum Typ des Properties
 */
class Demo {

    @Test
    void builtIn_propertyEditors() {

        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("propertyEditorBeans_context.xml");
        ctx.refresh();

        DataBean builtInSample = (DataBean) ctx.getBean("builtInSample");
        System.out.println(builtInSample);

        ctx.close();
    }
}



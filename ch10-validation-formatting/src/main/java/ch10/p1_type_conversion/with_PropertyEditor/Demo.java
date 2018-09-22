package ch10.p1_type_conversion.with_PropertyEditor;

import ch10.Singer;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

/*
 * PropertyEditor: String => POJO
 *
 * the known way from ch3
 */
class Demo {

    @Test
    void main() {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("prop-editor-context.xml");
        ctx.refresh();

        Singer eric = ctx.getBean("eric", Singer.class);
        System.out.println(eric);

        Singer countrySinger = ctx.getBean("countrySinger", Singer.class);
        System.out.println(countrySinger);

        ctx.close();
    }
}

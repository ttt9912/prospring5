package ch4.p10_JSR330;

import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

/*
 * Spring supports the JavaEE JSR 330 Annotations
 */
class Demo {

    @Test
    void di_with_jsr330(){
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("di_jsr330_context.xml");
        ctx.refresh();

        MessageRenderer renderer = ctx.getBean("messageRenderer", MessageRenderer.class);
        renderer.render();

        ctx.close();
    }

}

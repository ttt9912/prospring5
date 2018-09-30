package ch5.p5_declarative_aop.with_aspectJ;

import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

class Demo {

    @Test
    void test() {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("aspectJ_context.xml");
        ctx.refresh();

        Documentarist documentarist = ctx.getBean("documentarist", Documentarist.class);
        documentarist.execute();
    }
}

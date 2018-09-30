package ch5.p5_declarative_aop.with_aopNamespace;

import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

class Demo {

    @Test
    void test() {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("aopNamespace_context.xml");
        ctx.refresh();

        NewDocumentarist documentarist = ctx.getBean("documentarist", NewDocumentarist.class);
        documentarist.execute();

        ctx.close();
    }
}

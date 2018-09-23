package ch11.p1_task_scheduler.with_context_xml;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

class Demo {

    @Test
    void with_context_xml() throws IOException {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(AppConfig.class);

        CarServiceImpl carService = ctx.getBean("carService", CarServiceImpl.class);

        System.in.read(); // let the application run
        ctx.close();
    }
}
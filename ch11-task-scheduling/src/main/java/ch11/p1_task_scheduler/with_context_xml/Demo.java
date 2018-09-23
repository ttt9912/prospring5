package ch11.p1_task_scheduler.with_context_xml;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

class Demo {

    @Test
    void with_context_xml() throws IOException, InterruptedException {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(AppConfig.class);

        CarServiceImpl carService = ctx.getBean("carService", CarServiceImpl.class);


        Thread.sleep(15000); // let the app run a while...
        System.out.println("app terminated automatically");
        ctx.close();
    }
}
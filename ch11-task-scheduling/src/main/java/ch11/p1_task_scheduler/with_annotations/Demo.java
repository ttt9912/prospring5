package ch11.p1_task_scheduler.with_annotations;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

/*
 * To use scheduling Annotations,
 * @EnableScheduling is required in configuration or <task:annoation-driven> in xml
 */
class Demo {

    @Test
    void with_annotations() throws IOException, InterruptedException {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(AnnotationAppConfig.class);


        Thread.sleep(15000); // let the ch12.server run a while...
        System.out.println("ch12.server terminated automatically");
        ctx.close();
    }
}

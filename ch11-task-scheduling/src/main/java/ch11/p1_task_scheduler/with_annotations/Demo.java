package ch11.p1_task_scheduler.with_annotations;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

/*
 * To use scheduling Annotations,
 * @EnableScheduling is required in configuration or <task:annoation-driven> in xml
 */
class Demo {

    @Test
    void with_annotations() throws IOException {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(AnnotationAppConfig.class);


        System.in.read(); // let the app run...

        ctx.close();
    }
}

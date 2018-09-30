package ch11.p2_async_tasks;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.concurrent.Future;

class Demo {

    @Test
    void async_task() {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(AsyncAppConfig.class);

        AsyncService asyncService = ctx.getBean("asyncService", AsyncService.class);

        for (int i = 0; i < 5; i++) {
            asyncService.asyncTask();
        }

        ctx.close();
    }

    @Test
    void async_task_with_return_value() throws InterruptedException, IOException {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(AsyncAppConfig.class);

        AsyncService asyncService = ctx.getBean("asyncService", AsyncService.class);

        Future<String> result1 = asyncService.asyncWithResult("John Mayer");
        Future<String> result2 = asyncService.asyncWithResult("Eric Clapton");
        Future<String> result3 = asyncService.asyncWithResult("BB King");

        Thread.sleep(4000);

        System.out.println("result1: " + result1);
        System.out.println("result2: " + result2);
        System.out.println("result3: " + result3);

        ctx.close();
    }
}

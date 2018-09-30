package ch11.p3_task_executors;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/*
 * ThreadExecutor: execute() takes Runnable as argument
 *
 * Spring provides various ThreadExecutor implementations
 * - SimpleAsyncTaskExecutor
 * - SyncTaskExecutor
 * - ThreadPoolTaskExecutor
 */
class Demo {

    @Test
    void simpleAsyncTaskExecutor() {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(AppConfig.class);

        SimpleAsyncTaskExecutor simpleAsyncTaskExecutor =
                ctx.getBean("simpleAsyncTaskExecutor", SimpleAsyncTaskExecutor.class);

        executeTask(simpleAsyncTaskExecutor);

        ctx.close();
    }

    @Test
    void syncTaskExecutor() {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(AppConfig.class);

        SyncTaskExecutor syncTaskExecutor =
                ctx.getBean("syncTaskExecutor", SyncTaskExecutor.class);

        executeTask(syncTaskExecutor);

        ctx.close();
    }

    @Test
    void threadPoolTaskExecutor() {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(AppConfig.class);

        ThreadPoolTaskExecutor threadPoolTaskExecutor =
                ctx.getBean("threadPoolTaskExecutor", ThreadPoolTaskExecutor.class);

        executeTask(threadPoolTaskExecutor);

        ctx.close();
    }


    private void executeTask(final TaskExecutor taskExecutor) {
        for (int i = 0; i < 10; i++) {
            taskExecutor.execute(
                    () -> System.out.println("Hello from Thread: " + Thread.currentThread().getName()));
        }
    }
}

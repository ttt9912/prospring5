package ch11.p3_task_executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.SchedulingTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
//@EnableAsync not used
class AppConfig {

    @Bean
    TaskExecutor simpleAsyncTaskExecutor() {
        // SimpleAsyncTaskExecutor: async, creates a new thread on each execution
        return new SimpleAsyncTaskExecutor();
    }

    @Bean
    TaskExecutor syncTaskExecutor() {
        // SyncTaskExecutor: not async, invocation occurs in the calling thread
        return new SyncTaskExecutor();
    }

    @Bean
    SchedulingTaskExecutor threadPoolTaskExecutor() {
        // ThreadPoolTaskExecutor: async, configurable, pool size is 1 per default
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(5);
        return taskExecutor;
    }

}

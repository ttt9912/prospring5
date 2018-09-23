package ch11.p1_task_scheduler.with_annotations;

import ch11.config.JpaConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

/*
 * @EnableScheduling: enables detection of @Scheduled annotations
 *                    on any spring beans
 *
 * @EnableScheduling = <task:annoation-driven>
 *
 * Spring automatically looks for a scheduler definition:
 * - unique TaskScheduler bean
 * - TaskScheduler named 'taskScheduler'
 * - ScheduledExecutorService bean
 * if none is found, a single threaded default scheduler will be created
 */
@Configuration
@EnableScheduling
@Import({JpaConfig.class})
@ComponentScan
        // detect CarServiceImpl
class AnnotationAppConfig {

    // uncomment this to use the TaskScheduler explicitly declared bean
	/*
	@Bean TaskScheduler carScheduler() {
		ThreadPoolTaskScheduler carScheduler = new ThreadPoolTaskScheduler();
		carScheduler.setPoolSize(10);
		return carScheduler;
	}
	*/
}

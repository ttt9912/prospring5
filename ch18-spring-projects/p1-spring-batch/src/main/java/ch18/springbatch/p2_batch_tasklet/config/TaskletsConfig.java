package ch18.springbatch.p2_batch_tasklet.config;

import ch18.springbatch.config.DataSourceConfig;
import ch18.springbatch.p2_batch_tasklet.tasklets.JsonSingersReader;
import ch18.springbatch.p2_batch_tasklet.tasklets.SingersProcessor;
import ch18.springbatch.p2_batch_tasklet.tasklets.SingersToDBWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/*
 * Job consists of several Steps
 * each Step executes a Tasklet
 *
 * This Job consists of three steps (Tasklet implementations):
 *  - read from csv
 *  - transform data
 *  - write to db
 */
@Configuration
@EnableBatchProcessing
@Import({DataSourceConfig.class, JdbcConfig.class})
@ComponentScan("ch18.springbatch.p2_batch_tasklet")
public class TaskletsConfig {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    // -----------------------------------------------------------------------------
    // Job
    // -----------------------------------------------------------------------------

    @Bean
    public Job job() {
        return jobs
                .get("taskletJob")
                .start(readSingers())
                .next(processSingers())
                .next(writeSingersToDB())
                .build();
    }

    // -----------------------------------------------------------------------------
    // Steps
    // -----------------------------------------------------------------------------

    @Bean
    public Step readSingers() {
        return steps
                .get("readSingers")
                .tasklet(csvSingersReader())
                .build();
    }

    @Bean
    public Step processSingers() {
        return steps
                .get("processSingers")
                .tasklet(singersProcessor())
                .build();
    }

    @Bean
    public Step writeSingersToDB() {
        return steps
                .get("writeSingersToDB")
                .tasklet(singersToDBWriter())
                .build();
    }

    // -----------------------------------------------------------------------------
    // Tasklets
    // -----------------------------------------------------------------------------

    @Bean
    Tasklet csvSingersReader() {
        return new JsonSingersReader();
    }

    @Bean
    Tasklet singersProcessor() {
        return new SingersProcessor();
    }

    @Bean
    Tasklet singersToDBWriter() {
        return new SingersToDBWriter();
    }
}

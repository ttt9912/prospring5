package ch18.springbatch.p1_batch_chunk;

import ch18.springbatch.p1_batch_chunk.config.BatchConfig;
import ch18.springbatch.util.JdbcUtil;
import org.junit.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import javax.sql.DataSource;
import java.util.Date;

/*
 * Batch Job: can contain one or more Steps
 *
 * Step: can execute a single unit of work (Tasklet implementation)
 *       or participate in Chunk-oriented-processing
 *
 * Chunk-oriented-processing: each Step utilizes an:
 *      - ItemReader: read data from csv
 *      - ItemProcessor: transform data (optional)
 *      - ItemWriter: write data to db
 *
 * StepExecutionListener: resides at step level
 */
public class Demo {

    @Test
    public void with_configuration() throws Exception {
        GenericApplicationContext ctx =
                new AnnotationConfigApplicationContext(BatchConfig.class);

        Job job = ctx.getBean(Job.class);
        JobLauncher jobLauncher = ctx.getBean(JobLauncher.class);
        JobParameters jobParameters = new JobParametersBuilder()
                .addDate("date", new Date()).toJobParameters();

        jobLauncher.run(job, jobParameters);

        printSingerTable(ctx.getBean(DataSource.class));

        ctx.close();
    }

    @Test
    public void with_context_xml() {
        // todo
    }


    private void printSingerTable(DataSource dataSource) {
        JdbcUtil jdbcUtil = new JdbcUtil(dataSource);
        System.out.println("\nselect * from singer");
        jdbcUtil.executeQuery("select * from singer")
                .forEach(System.out::println);
    }

}

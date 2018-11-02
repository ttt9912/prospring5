package ch18.springbatch.p2_batch_tasklet;

import ch18.springbatch.p2_batch_tasklet.config.TaskletsConfig;
import ch18.springbatch.util.JdbcUtil;
import org.junit.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import javax.sql.DataSource;
import java.util.Date;

/*
 * Testing Spring Batch: see /test/
 */
public class Demo {

    @Test
    public void tasklet() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        GenericApplicationContext ctx =
                new AnnotationConfigApplicationContext(TaskletsConfig.class);

        Job job = ctx.getBean(Job.class);
        JobLauncher jobLauncher = ctx.getBean(JobLauncher.class);

        JobParameters jobParameters = new JobParametersBuilder()
                .addDate("date", new Date()).toJobParameters();

        jobLauncher.run(job, jobParameters);

        printSingerTable(ctx.getBean(DataSource.class));

        ctx.close();
    }

    private void printSingerTable(final DataSource dataSource) {
        JdbcUtil jdbcUtil = new JdbcUtil(dataSource);
        System.out.println("\nselect * from singer");
        jdbcUtil.executeQuery("select * from singer")
                .forEach(System.out::println);
    }
}

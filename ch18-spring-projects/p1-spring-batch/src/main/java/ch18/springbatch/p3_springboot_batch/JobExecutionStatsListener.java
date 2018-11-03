package ch18.springbatch.p3_springboot_batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/*
 * Instead of StepExecutionListenerSupport, a JobExecutionListenerSupport
 * implementation is created.
 *
 * JobExecutionListenerSupport: provides callbacks in the lifecycle of a
 *                              job via beforeJob() and afterJob()
 */
@Component
public class JobExecutionStatsListener extends JobExecutionListenerSupport {
    private static final Logger log = LoggerFactory.getLogger(JobExecutionStatsListener.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired // todo: directly autowire??
    public JobExecutionStatsListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterJob(final JobExecution jobExecution) {

        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("singers were saved to the db");
            System.out.println("\nselect * from singer");
            jdbcTemplate.query("select * from singer",
                    (rs, i) -> rs.getString(1) + " " +
                            rs.getString(2) + " " +
                            rs.getString(3))
                    .forEach(System.out::println);
        }
    }
}

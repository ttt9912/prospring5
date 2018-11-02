package ch18.springbatch.p2_batch_tasklet.tasklets;

import ch18.springbatch.data.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;

import java.util.List;

/*
 * NamedParameterJdbcTemplate: is NOT spring batch specific, just a way
 *                             to batch insert to DB
 * (https://www.baeldung.com/spring-jdbc-jdbctemplate)
 */
public class SingersToDBWriter implements Tasklet, StepExecutionListener {
    private static final Logger log = LoggerFactory.getLogger(SingersToDBWriter.class);

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    private List<Singer> singers;

    @Override
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {

        final SqlParameterSource[] insertBatch = SqlParameterSourceUtils.createBatch(singers.toArray());

        jdbcTemplate.batchUpdate("insert into singer (first_name, last_name, song) values (:firstName, :lastName, :song)",
                insertBatch);

        return RepeatStatus.FINISHED;
    }

    @Override
    public void beforeStep(final StepExecution stepExecution) {
        // retrieve singers from Job context
        ExecutionContext executionContext = stepExecution
                .getJobExecution()
                .getExecutionContext();
        this.singers = (List<Singer>) executionContext.get("singers");
    }

    @Override
    public ExitStatus afterStep(final StepExecution stepExecution) {
        log.info("completed writing singers to DB");
        return ExitStatus.COMPLETED;
    }
}

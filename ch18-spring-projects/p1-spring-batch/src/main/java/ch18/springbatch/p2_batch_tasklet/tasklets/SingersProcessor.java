package ch18.springbatch.p2_batch_tasklet.tasklets;

import ch18.springbatch.p2_batch_tasklet.Singer;
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

import java.util.List;

public class SingersProcessor implements Tasklet, StepExecutionListener {
    private static final Logger log = LoggerFactory.getLogger(SingersProcessor.class);

    private List<Singer> singers;

    @Override
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {

        singers.forEach(singer -> {
            singer.setFirstName(singer.getFirstName().toUpperCase());
            singer.setLastName(singer.getLastName().toUpperCase());
        });

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
        log.info("processing of singers completed");
        return ExitStatus.COMPLETED;
    }
}

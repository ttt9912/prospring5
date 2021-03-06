package ch18.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.stereotype.Component;

@Component
public class StepExecutionStatsListener extends StepExecutionListenerSupport {
    private static Logger log = LoggerFactory.getLogger(StepExecutionStatsListener.class);

    @Override
    public ExitStatus afterStep(final StepExecution stepExecution) {
        log.info("step {} wrote {} items", stepExecution.getStepName(),
                stepExecution.getWriteCount());
        return null;
    }
}

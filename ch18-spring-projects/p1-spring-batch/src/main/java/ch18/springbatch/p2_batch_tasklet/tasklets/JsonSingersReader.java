package ch18.springbatch.p2_batch_tasklet.tasklets;

import ch18.springbatch.data.Singer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.util.List;

/*
 * StepExecution: Job's context (make data available for next step)
 */
public class JsonSingersReader implements Tasklet, StepExecutionListener {
    private static final Logger log = LoggerFactory.getLogger(JsonSingersReader.class);

    @Autowired
    private ResourceLoader resourceLoader;

    private ObjectMapper objectMapper = new ObjectMapper();

    private List<Singer> singers;

    @Override
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {
        Resource resource = resourceLoader.getResource("classpath:data/test-data.json");

        singers = objectMapper.readValue(resource.getInputStream(),
                new TypeReference<List<Singer>>() {
                });

        return RepeatStatus.FINISHED;
    }

    @Override
    public void beforeStep(final StepExecution stepExecution) {
        log.info("starting to read json");
    }

    @Override
    public ExitStatus afterStep(final StepExecution stepExecution) {
        log.info("read {} singers from json", singers.size());

        // make Singers available for the next Step
        stepExecution.getJobExecution()
                .getExecutionContext()
                .put("singers", singers);

        return ExitStatus.COMPLETED;
    }
}

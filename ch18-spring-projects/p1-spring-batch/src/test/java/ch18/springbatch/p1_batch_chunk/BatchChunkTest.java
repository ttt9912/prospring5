package ch18.springbatch.p1_batch_chunk;

import ch18.springbatch.TestConfig;
import ch18.springbatch.p1_batch_chunk.config.BatchConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BatchConfig.class, TestConfig.class})
public class BatchChunkTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Test
    public void givenChunksJob_whenJobEnds_thenStatusCompleted()
            throws Exception {

        JobExecution jobExecution = jobLauncherTestUtils.launchJob();

        assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
    }
}
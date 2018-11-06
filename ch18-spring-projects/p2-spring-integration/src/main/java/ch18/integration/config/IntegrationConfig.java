package ch18.integration.config;

import ch18.integration.MessageToJobLauncher;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.integration.launch.JobLaunchingGateway;
import org.springframework.batch.integration.launch.JobLaunchingMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.integration.annotation.*;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.filters.SimplePatternFileListFilter;
import org.springframework.messaging.MessageChannel;

import java.io.File;

/*
 * @EnableIntegration:
 *
 * @IntegrationComponentScan:
 */
@Configuration
@EnableIntegration
@IntegrationComponentScan
@Import(BatchConfig.class)
public class IntegrationConfig {

    @Autowired
    private Job job;

    @Bean
    public MessageChannel inbound() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel outbound() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel loggingChannel() {
        return new DirectChannel();
    }

    /*
     * @InboundChannelAdapter: watching a specific directory
     */
    @Bean
    @InboundChannelAdapter(channel = "inbound", poller = @Poller(fixedRate = "5000"))
    public MessageSource<File> inboundFileChannelAdapter() {
        FileReadingMessageSource source = new FileReadingMessageSource();
        source.setDirectory(new File("file:/temp/"));
        source.setFilter(new SimplePatternFileListFilter("*.csv"));
        return source;
    }

    /*
     * receives files from "inbound" input channel
     * files are standard File objects wrapped in a Message
     */
    @Bean
    @Transformer(inputChannel = "inbound", outputChannel = "outbound")
    public MessageToJobLauncher messageToJobLauncher() {
        return new MessageToJobLauncher(job, "file.name");
    }

    /*
     * JobLaunchingGateway: receives job launch requests from Transformer
     * and invokes the Batch Job
     */
    @Bean
    @ServiceActivator(inputChannel = "outbound")
    public JobLaunchingGateway jobLaunchingGateway(JobLauncher jobLauncher) {
        JobLaunchingGateway jg = new JobLaunchingGateway(jobLauncher);
        jg.setOutputChannel(loggingChannel());
        return jg;
    }

    /*
     * Logging
     */
    @Bean
    @ServiceActivator(inputChannel = "outbound", outputChannel = "loggingChannel")
    protected JobLaunchingMessageHandler launcher(JobLauncher jobLauncher) {
        return new JobLaunchingMessageHandler(jobLauncher);
    }
}

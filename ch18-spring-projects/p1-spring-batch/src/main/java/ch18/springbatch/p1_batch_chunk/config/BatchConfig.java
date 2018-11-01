package ch18.springbatch.p1_batch_chunk.config;

import ch18.springbatch.data.Singer;
import ch18.springbatch.p1_batch_chunk.StepExecutionStatsListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ResourceLoader;

import javax.sql.DataSource;

/*
 * @EnableBatchProcessing: provides base config for building batch jobs
 *      - creates an instance of StepScope
 *      - Beans of type JobRepository, JobLauncher, JobBuilderFactory,
 *        StepBuilderFactory are made available for autowiring
 *
 * step1 bean is set up for chunk-oriented processing, which requires ItemReader and ItemWriter
 */
@Configuration
@EnableBatchProcessing
@Import(DataSourceConfig.class)
@ComponentScan("ch18.springbatch.p1_batch")
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private StepExecutionStatsListener stepExecutionStatsListener;

    @Bean
    public Job job(@Qualifier("step1") Step step) {
        return jobs.get("singerJob").start(step).build();
    }

    @Bean
    public Step step1(ItemReader<Singer> itemReader, ItemProcessor<Singer, Singer> itemProcessor,
                      ItemWriter<Singer> itemWriter) {

        return steps.get("step1")
                .listener(stepExecutionStatsListener)
                .<Singer, Singer>chunk(10)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();
    }

    @Bean
    public ItemReader itemReader() {
        FlatFileItemReader itemReader = new FlatFileItemReader();
        itemReader.setResource(resourceLoader.getResource("classpath:data/test-data.csv"));
        DefaultLineMapper lineMapper = new DefaultLineMapper();

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames("firstName", "lastName", "song");
        tokenizer.setDelimiter(",");
        lineMapper.setLineTokenizer(tokenizer);

        BeanWrapperFieldSetMapper<Singer> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Singer.class);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        itemReader.setLineMapper(lineMapper);
        return itemReader;
    }

    @Bean
    public ItemWriter itemWriter() {
        JdbcBatchItemWriter<Singer> itemWriter = new JdbcBatchItemWriter<>();
        itemWriter.setItemSqlParameterSourceProvider(
                new BeanPropertyItemSqlParameterSourceProvider<>()
        );

        itemWriter.setSql("INSERT INTO singer (first_name, last_name, song) " +
                "VALUES (:firstName, :lastName, :song)");
        itemWriter.setDataSource(dataSource);
        return itemWriter;
    }
}

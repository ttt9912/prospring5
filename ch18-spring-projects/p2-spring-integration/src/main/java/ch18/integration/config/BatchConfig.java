package ch18.integration.config;

import ch18.integration.Singer;
import ch18.integration.StepExecutionStatsListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ResourceLoader;

import javax.sql.DataSource;

/*
 * @StepScope: itemReader needs to be created every time a new file is processed,
 *             therefore no longer singleton scope.
 *             short form for: @Scope(value="step", proxyMode=TARGET_CLASS)
 */
@Configuration
@EnableBatchProcessing
@Import(DataSourceConfig.class)
@ComponentScan("ch18.integration")
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

    /*
     * file.name is set inside IntegrationConfig
     */
    @Bean
    @StepScope
    public FlatFileItemReader itemReader(@Value("file://#{jobParameters['file.name']}") String filePath) {
        FlatFileItemReader itemReader = new FlatFileItemReader();
        itemReader.setResource(resourceLoader.getResource(filePath));
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

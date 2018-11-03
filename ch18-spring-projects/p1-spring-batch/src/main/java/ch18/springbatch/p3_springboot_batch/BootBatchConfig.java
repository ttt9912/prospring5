package ch18.springbatch.p3_springboot_batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/*
 * flow(): execute a step or a sequence of steps
 */
@Configuration
@EnableBatchProcessing
public class BootBatchConfig {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SingerItemProcessor itemProcessor;

    @Autowired
    private EntityManagerFactory emf;

    @Bean
    public Job job(JobExecutionStatsListener listener) {
        return jobs.get("singerJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1())
                .end()
                .build();
    }

    private Step step1() {
        return steps.get("step1")
                .<Singer, Singer>chunk(10)
                .reader(itemReader())
                .processor(itemProcessor)
                .writer(itemWriter())
                .build();
    }

    // same as without Spring Boot but with builder
    private ItemReader<Singer> itemReader() {

        return new FlatFileItemReaderBuilder<Singer>()
                .name("personItemReader")
                .resource(new ClassPathResource("data/test-data.csv"))
                .delimited()
                .names(new String[]{"firstName", "lastName", "song"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Singer>() {{
                    setTargetType(Singer.class);
                }})
                .build();
    }

    private ItemWriter<Singer> itemWriter() {
        JpaItemWriter<Singer> singerJpaItemWriter = new JpaItemWriter<>();
        singerJpaItemWriter.setEntityManagerFactory(emf);

        try {
            singerJpaItemWriter.afterPropertiesSet();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return singerJpaItemWriter;
    }
}

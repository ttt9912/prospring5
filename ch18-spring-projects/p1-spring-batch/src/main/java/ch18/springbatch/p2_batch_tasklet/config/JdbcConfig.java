package ch18.springbatch.p2_batch_tasklet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class JdbcConfig {

    @Bean
    public NamedParameterJdbcTemplate jdbcTemplate() {
        final NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource());
        return jdbcTemplate;
    }

    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder dbBuilder = new EmbeddedDatabaseBuilder();
        return dbBuilder.setType(EmbeddedDatabaseType.H2)
                // schema-h2.sql provides DML for creating Spring Batchs utility tables
                .addScripts("classpath:/org/springframework/batch/core/schema-h2.sql",
                        "classpath:/sql/singer.sql")
                .build();
    }
}

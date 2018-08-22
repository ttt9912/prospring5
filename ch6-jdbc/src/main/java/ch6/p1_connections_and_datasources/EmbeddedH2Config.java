package ch6.p1_connections_and_datasources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/*
 * EmbeddedDatabase: extends DataSource
 */

@Configuration
class EmbeddedH2Config {

    private static Logger logger = LoggerFactory.getLogger(EmbeddedH2Config.class);

    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();

        return builder
                .setType(EmbeddedDatabaseType.H2)
                .addScripts("classpath:h2/schema.sql", "classpath:h2/test-data.sql")
                .build();
    }
}

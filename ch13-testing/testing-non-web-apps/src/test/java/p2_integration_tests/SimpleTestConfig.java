package p2_integration_tests;

import ch13.init.DbInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/*
 * Test Profile
 *  - configure test DataSource
 *  - exclude initialization of production data (DbInitializer)
 */
@Profile("test")
@Configuration
@ComponentScan(basePackages = {"ch13"},
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
                value = DbInitializer.class)})
public class SimpleTestConfig {
    private static final Logger logger = LoggerFactory.getLogger(SimpleTestConfig.class);

    @Bean
    public DataSource dataSource() {
        try {
            EmbeddedDatabaseBuilder dbBuilder = new EmbeddedDatabaseBuilder();
            return dbBuilder.setType(EmbeddedDatabaseType.H2).build();
        } catch (Exception e) {
            logger.error("Embedded DataSource bean cannot be created!", e);
            return null;
        }

    }
}

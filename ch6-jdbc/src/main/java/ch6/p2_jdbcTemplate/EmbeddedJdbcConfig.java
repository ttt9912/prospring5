package ch6.p2_jdbcTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
class EmbeddedJdbcConfig {
    private static Logger logger = LoggerFactory.getLogger(EmbeddedJdbcConfig.class);

    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();

        return builder
                .setType(EmbeddedDatabaseType.H2)
                .addScripts("classpath:sql/schema.sql", "classpath:sql/test-data.sql")
                .build();
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }

    @Bean
    public SingerDAO singerDAO() {
        JdbcSingerDAO singerDAO = new JdbcSingerDAO();
        singerDAO.setJdbcTemplate(jdbcTemplate());
        return singerDAO;
    }
}

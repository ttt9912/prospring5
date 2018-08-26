package ch6.p3_spring_jdbc_operations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
class EmbeddedJdbcConfig {

    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();

        return builder
                .setType(EmbeddedDatabaseType.H2)
                .addScripts("classpath:sql/schema.sql", "classpath:sql/test-data.sql")
                .build();
    }

    /*
     * dataSource is injected automatically due to @Resource(name = "dataSource")
     * no need to call singerDAO.setDataSource
     */
    @Bean
    public SingerDAO singerDAO() {
        JdbcSingerDAO singerDAO = new JdbcSingerDAO();
        return singerDAO;
    }
}

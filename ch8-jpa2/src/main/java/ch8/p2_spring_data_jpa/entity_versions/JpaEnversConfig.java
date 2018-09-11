package ch8.p2_spring_data_jpa.entity_versions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"ch8.p2_spring_data_jpa.entity_versions"})
@EnableJpaRepositories(basePackages = "ch8.p2_spring_data_jpa.entity_versions")
class JpaEnversConfig {
    private static Logger logger = LoggerFactory.getLogger(JpaEnversConfig.class);

    @Bean
    public DataSource dataSource() {
        final EmbeddedDatabaseBuilder dbBuilder = new EmbeddedDatabaseBuilder();

        return dbBuilder.setType(EmbeddedDatabaseType.H2)
                .addScripts("classpath:spring_data_jpa_sql/p3_sql/p3-schema.sql", "classpath:spring_data_jpa_sql/p3_sql/p3-test-data.sql")
                .build();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory());
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean =
                new LocalContainerEntityManagerFactoryBean();
        factoryBean.setPackagesToScan("ch8.p2_spring_data_jpa.entity_versions");
        factoryBean.setDataSource(dataSource());
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        factoryBean.setJpaProperties(hibernateProperties());
        factoryBean.afterPropertiesSet();
        return factoryBean.getNativeEntityManagerFactory();
    }

    @Bean
    public Properties hibernateProperties() {
        final Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        hibernateProperties.put("hibernate.format_sql", false);
        hibernateProperties.put("hibernate.use_sql_comments", true);
        //hibernateProperties.put("hibernate.show_sql", true);
        hibernateProperties.put("hibernate.max_fetch_depth", 3);
        hibernateProperties.put("hibernate.jdbc.batch_size", 10);
        hibernateProperties.put("hibernate.jdbc.fetch_size", 50);

        // hibernate envers properties
        hibernateProperties.put("org.hibernate.envers.audit_table_suffix", "_H");
        hibernateProperties.put("org.hibernate.envers.revision_field_name", "AUDIT_REVISION");
        hibernateProperties.put("org.hibernate.envers.revision_type_field_name", "ACTION_TYPE");
        hibernateProperties.put("org.hibernate.envers.audit_strategy", "org.hibernate.envers.strategy.ValidityAuditStrategy");
        hibernateProperties.put("org.hibernate.envers.audit_strategy_validity_end_rev_field_name", "AUDIT_REVISION_END");
        hibernateProperties.put("org.hibernate.envers.audit_strategy_validity_store_revend_timestamp", "True");
        hibernateProperties.put("org.hibernate.envers.audit_strategy_validity_revend_timestamp_field_name", "AUDIT_REVISION_END_TS");

        return hibernateProperties;
    }
}

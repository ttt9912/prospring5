package ch9.p1_transactions.with_annotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;

/*
 * Transaction Management Beans Configuration
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "ch9.p1_transactions.with_annotations")
class ServicesConfig {

    @Autowired
    private EntityManagerFactory emf;

    @Bean
    PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(emf);
    }
}

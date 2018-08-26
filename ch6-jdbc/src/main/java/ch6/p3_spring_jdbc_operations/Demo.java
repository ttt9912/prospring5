package ch6.p3_spring_jdbc_operations;

import ch6.entity.Singer;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.sql.Date;
import java.util.List;

class Demo {

    @Test
    void mappingSqlQuery() {
        /*
         * Execute query & map ResultSet to domain object
         *
         * only for mapping single entities, for relationships, use
         * JdbcTemplate with ResultSetExtractor
         */
        GenericApplicationContext ctx =
                new AnnotationConfigApplicationContext(EmbeddedJdbcConfig.class);
        SingerDAO singerDAO = ctx.getBean("singerDAO", SingerDAO.class);

        // find all
        List<Singer> singers = singerDAO.findAll();
        singers.forEach(System.out::println);

        // with parameters
        List<Singer> byFirstName = singerDAO.findByFirstName("John");
        byFirstName.forEach(System.out::println);

        ctx.close();
    }

    @Test
    void sqlUpdate_update() {
        GenericApplicationContext ctx =
                new AnnotationConfigApplicationContext(EmbeddedJdbcConfig.class);
        SingerDAO singerDAO = ctx.getBean("singerDAO", SingerDAO.class);

        final Singer singer = new Singer();
        singer.setId(1L);
        singer.setFirstName("John Clayton");
        singer.setLastName("Mayer");
        singer.setBirthDate(Date.valueOf("1977-09-16"));

        singerDAO.update(singer);

        List<Singer> singers = singerDAO.findAll();
        singers.forEach(System.out::println);

        ctx.close();
    }

    @Test
    void sqlUpdate_insert() {

    }

    @Test
    void batchUpdate() {

    }
}

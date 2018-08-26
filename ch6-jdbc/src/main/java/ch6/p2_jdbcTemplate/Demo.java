package ch6.p2_jdbcTemplate;

import ch6.entity.Singer;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

class Demo {


    // ---------------------------------------------------------------------------
    // Simple Queries
    // ---------------------------------------------------------------------------

    @Test
    void simple_queries() {
        GenericApplicationContext ctx =
                new AnnotationConfigApplicationContext(EmbeddedJdbcConfig.class);

        SingerDAO singerDAO = ctx.getBean("singerDAO", SingerDAO.class);

        // simple query
        String result1 = singerDAO.findNameById(1L);
        System.out.println("Name for id {1}: " + result1);

        // named parameters
        String result2 = singerDAO.findFirstNameById(1L);
        System.out.println("Firstname for id {1}: " + result2);

        ctx.close();
    }


    // ---------------------------------------------------------------------------
    // Map DB Rows to POJOs
    // ---------------------------------------------------------------------------

    @Test
    void rowMapper() {
        // map db rows to simple POJOS. Relationships are ignored

        GenericApplicationContext ctx =
                new AnnotationConfigApplicationContext(EmbeddedJdbcConfig.class);

        SingerDAO singerDAO = ctx.getBean("singerDAO", SingerDAO.class);

        List<Singer> all = singerDAO.findAll();
        all.forEach(System.out::println);
    }

    @Test
    void resultSetExtractor() {
        // mapping Relationships

        GenericApplicationContext ctx =
                new AnnotationConfigApplicationContext(EmbeddedJdbcConfig.class);

        SingerDAO singerDAO = ctx.getBean("singerDAO", SingerDAO.class);

        List<Singer> all = singerDAO.findAllWithAlbums();
        all.forEach(System.out::println);
    }

}

package ch6.p3_spring_jdbc_operations;

import ch6.entity.Album;
import ch6.entity.Singer;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

class Demo {

    @Test
    void mappingSqlQuery() {
        /*
         * Execute query & map ResultSet to domain object
         *
         * only for mapping single entity, for relationships, use
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
        GenericApplicationContext ctx =
                new AnnotationConfigApplicationContext(EmbeddedJdbcConfig.class);
        SingerDAO singerDAO = ctx.getBean("singerDAO", SingerDAO.class);

        final Singer singer = new Singer();
        singer.setId(1L);
        singer.setFirstName("Ed");
        singer.setLastName("Sheeran");
        singer.setBirthDate(Date.valueOf("1991-01-17"));

        singerDAO.insert(singer);

        List<Singer> singers = singerDAO.findAll();
        singers.forEach(System.out::println);

        ctx.close();
    }

    @Test
    void batchUpdate() {
        GenericApplicationContext ctx =
                new AnnotationConfigApplicationContext(EmbeddedJdbcConfig.class);
        SingerDAO singerDAO = ctx.getBean("singerDAO", SingerDAO.class);

        final Singer singer = new Singer();
        singer.setId(1L);
        singer.setFirstName("BB");
        singer.setLastName("King");
        singer.setBirthDate(Date.valueOf("1940-08-16"));

        final Album album1 = new Album();
        album1.setTitle("My Kind of Blues");
        album1.setReleaseDate(Date.valueOf("1961-07-08"));
        final Album album2 = new Album();
        album2.setTitle("A Heart full of Blues");
        album2.setReleaseDate(Date.valueOf("1962-03-20"));
        singer.setAlbums(Arrays.asList(album1, album2));

        singerDAO.insertWithAlbum(singer);

        System.out.println("findAllWithAlbums() is not implemented");
        System.out.println("\tJdbcTemplate is required for mapping Relationships");

        ctx.close();
    }

    @Test
    void sqlFunction() {
        // H2 does not support user defined functions
        // -> Error while trying to load stored-function.sql
    }
}

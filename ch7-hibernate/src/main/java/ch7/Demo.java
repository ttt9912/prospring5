package ch7;

import ch7.entity.Album;
import ch7.entity.Singer;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.sql.Date;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

class Demo {

    // TODO evtl noch weitere Hibernate Dependencies (p. 358)

    @Test
    void with_context_xml__generateTablesFromEntities() {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("configure_session_factory_context.xml");
        ctx.refresh();

        SingerDao singerDao = ctx.getBean("singerDao", SingerDao.class);
        List<Singer> singers = singerDao.findAll();
        singers.forEach(System.out::println);

        ctx.close();
    }

    @Test
    void with_configuration__createTablesWithSQL() {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(SessionFactoryConfig.class);
        SingerDao singerDao = ctx.getBean(SingerDao.class);

        System.out.println("\nSingers:");
        List<Singer> singers = singerDao.findAll();
        singers.forEach(System.out::println);

        System.out.println("\nSingers with Albums:");
        List<Singer> singersWithAlbums = singerDao.findAllWithAlbums();
        singersWithAlbums.forEach(s -> System.out.println(s.withRelationships()));

        System.out.println("\nSinger for id=2:");
        Singer singerById = singerDao.findById(2L);
        System.out.println(singerById.withRelationships());

        ctx.close();
    }

    @Test
    void with_configuration__insert() {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(SessionFactoryConfig.class);
        SingerDao singerDao = ctx.getBean(SingerDao.class);

        final Singer singer = new Singer();
        singer.setFirstName("BB");
        singer.setLastName("King");
        singer.setBirthDate(Date.valueOf("1940-08-16"));

        final Album album1 = new Album();
        album1.setTitle("My Kind of Blues");
        album1.setReleaseDate(Date.valueOf("1961-07-08"));
        album1.setSinger(singer);
        final Album album2 = new Album();
        album2.setTitle("A Heart full of Blues");
        album2.setReleaseDate(Date.valueOf("1962-03-20"));
        album2.setSinger(singer);
        singer.setAlbums(new HashSet<>(Arrays.asList(album1, album2)));

        singerDao.save(singer);

        List<Singer> singers = singerDao.findAllWithAlbums();
        singers.forEach(s -> System.out.println(s.withRelationships()));

        ctx.close();
    }


}

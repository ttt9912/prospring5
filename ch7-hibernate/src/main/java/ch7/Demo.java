package ch7;

import ch7.entity.Singer;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;

class Demo {

    // TODO evtl noch weitere Hibernate Dependencies (p. 358)

    @Test
    void with_context_xml__generateTablesFromEntities() {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("configure_session_factory_context.xml");
        ctx.refresh();

        // TODO ?

        ctx.close();
    }

    @Test
    void with_configuration__createTablesWithSQL() {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(SessionFactoryConfig.class);

        System.out.println("\nSingers:");
        SingerDao singerDao = ctx.getBean(SingerDao.class);
        List<Singer> singers = singerDao.findAll();
        singers.forEach(System.out::println);

        System.out.println("\nSingers with Albums:");
        List<Singer> singersWithAlbums = singerDao.findAllWithAlbums();
        singersWithAlbums.forEach(singer -> System.out.println(singer.withRelationships()));

        System.out.println("\nSinger for id=2:");
        Singer singerById = singerDao.findById(2L);
        System.out.println(singerById);


        ctx.close();
    }


}

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

        SingerDao singerDao = ctx.getBean(SingerDao.class);
        List<Singer> singers = singerDao.findAll();
        singers.forEach(System.out::println);

        ctx.close();
    }


}

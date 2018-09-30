package ch8.p1_jpa2;

import ch8.entity.Singer;
import ch8.p1_jpa2.service.SingerService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

class JpaNativeQueryDemo {

    @Test
    void simple_native_query() {
        // ResultSet is mapped back to entity

        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(JpaConfig.class);
        SingerService singerService = ctx.getBean("jpaSingerService", SingerService.class);

        System.out.println("\n--- Singers by native query ---");
        List<Singer> singers = singerService.findAllByNativeQuery();
        singers.forEach(System.out::println);

        ctx.close();
    }

    @Test
    void native_query_with_resultSet_mapping() {
        // ResultSet mapping is defined on entity class level (@SqlResultSetMapping)

        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(JpaConfig.class);
        SingerService singerService = ctx.getBean("jpaSingerService", SingerService.class);

        System.out.println("\n--- Singers by native query ---");
        List<Singer> singers = singerService.findAllByNativeQuery();
        singers.forEach(System.out::println);

        ctx.close();
    }

}

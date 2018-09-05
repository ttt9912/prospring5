package ch8.p1_jpa;

import ch8.entity.Singer;
import ch8.p1_jpa.service.SingerService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

class CriteriaApiDemo {

    /*
     * Criteria API:
     *
     * - Use case: handling a large number of searchable fields
     *             with a large number of possible combinations
     *
     * - each Criteria is strongly typed
     *
     * - Criteria passed into the query is based on the mapped
     *   entity classes' meta model (generated)
     */

    @Test
    void criteria_query() {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(JpaConfig.class);
        SingerService singerService = ctx.getBean("jpaSingerService", SingerService.class);

        System.out.println("\n--- Singers by criteria query ---");
        List<Singer> singers = singerService.findByCriteriaQuery("John", "Mayer");
        singers.forEach(x -> System.out.println(x.withRelationships()));

        ctx.close();
    }
}

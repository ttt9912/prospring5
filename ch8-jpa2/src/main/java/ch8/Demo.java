package ch8;

import ch8.entity.Singer;
import ch8.service.SingerService;
import ch8.service.SingerSummaryUntypedImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

class Demo {

    @Test
    void named_queries() {
        // same as with pure Hibernate (ch7)

        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(JpaConfig.class);
        SingerService singerService = ctx.getBean("jpaSingerService", SingerService.class);

        System.out.println("\n--- Singers ---");
        List<Singer> singers = singerService.findAll();
        singers.forEach(System.out::println);

        System.out.println("\n--- Singers with Albums ---");
        List<Singer> singersWithAlbums = singerService.findAllWithAlbums();
        singersWithAlbums.forEach(s -> System.out.println(s.withRelationships()));

        System.out.println("\n--- Singer for id=2 ---");
        Singer singerById = singerService.findById(2L);
        System.out.println(singerById.withRelationships());

        ctx.close();
    }

    @Test
    void non_orm_mapped_results() {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(JpaConfig.class);
        SingerSummaryUntypedImpl singerSummaryUntyped =
                ctx.getBean("singerSummaryUntyped", SingerSummaryUntypedImpl.class);

        /*
         * untyped result
         *
         * ResultSet is a List of Object[] arrays.
         * Each Object[] array is a row.
         */
        System.out.println("\n--- SingerSummary: manual ResultSets ---");
        singerSummaryUntyped.displayAllSingerSummary();


        /*
         * custom result type (= View)
         *
         * Ideal if result contains data from different Tables:
         * view collects result in one object.
         */
        // todo

        ctx.close();
    }

}

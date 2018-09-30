package ch8.p1_jpa2;

import ch8.entity.Album;
import ch8.entity.Singer;
import ch8.p1_jpa2.service.SingerService;
import ch8.p1_jpa2.service.SingerSummaryService;
import ch8.p1_jpa2.view.SummarySinger;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Date;
import java.util.List;

class JpaDemo {

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
        SingerSummaryService singerSummaryUntyped =
                ctx.getBean("singerSummaryUntyped", SingerSummaryService.class);

        /*
         * untyped result
         *
         * ResultSet is a List of Object[] arrays.
         * Each Object[] array is a row.
         */
        System.out.println("\n--- SingerSummary: manual ResultSets ---");
        singerSummaryUntyped.displayAllUntyped();


        /*
         * custom result type (= View)
         *
         * Ideal if result contains data from different Tables:
         * view collects result in one object.
         */
        System.out.println("\n--- SingerSummary: SummarySinger View ---");
        List<SummarySinger> allView = singerSummaryUntyped.findAllView();
        allView.forEach(System.out::println);

        ctx.close();
    }

    @Test
    void insert_and_update() {
        // same as with pure Hibernate (difference: em.persist(E) and em.merge(E))

        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(JpaConfig.class);
        SingerService singerService = ctx.getBean("jpaSingerService", SingerService.class);

        /*
         * insert
         */
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
        singer.addAlbums(album1, album2);

        System.out.println("\n--- Insert new Singer ---");
        singerService.save(singer);

        /*
         * update
         */
        final Singer existingSinger = singerService.findById(4L);
        existingSinger.setFirstName("John Clayton");

        final Album existingAlbum = existingSinger.getAlbums().stream()
                .filter(a -> a.getTitle().equals("My Kind of Blues"))
                .findFirst().orElse(null);
        existingSinger.getAlbums().remove(existingAlbum);

        System.out.println("\n--- Update Singer with id=4 ---");
        singerService.save(existingSinger);

        ctx.close();
    }


}

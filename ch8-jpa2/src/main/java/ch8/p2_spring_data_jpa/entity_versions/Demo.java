package ch8.p2_spring_data_jpa.entity_versions;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Date;

/*
 * Selbe Ausganslage wie bei p2_tracking_entity changes.
 * Auditing wird verwendet, jedoch ist Auditing evtl. für Envers nicht zwingend nötig?
 */
class Demo {

    @Test
    void demo() {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(JpaEnversConfig.class);
        SingerService singerService = ctx.getBean(SingerService.class);


        System.out.println("\n--- Insert new Singer ---");
        Singer singer = new Singer();
        singer.setFirstName("BB");
        singer.setLastName("King");
        singer.setBirthDate((Date.valueOf("1940-08-16")));
        singerService.save(singer);

        System.out.println("\n--- Update Singer ---");
        Singer existingSinger = singerService.findById(4L);
        existingSinger.setFirstName("Riley B.");
        singerService.save(existingSinger);

        System.out.println("\n--- SingerAudits ---");
        singerService.findAll().forEach(System.out::println);


        System.out.println("\n--- History records with Revisions ---");

        Singer oldSinger = singerService.findByRevision(4L, 1);
        System.out.println("\nOld Singer with id=4 and revision=1:\n\t" + oldSinger);

        Singer upToDateSinger = singerService.findByRevision(4L, 2);
        System.out.println("\nUp to date Singer with id=4 and revision=2:\n\t" + upToDateSinger);

        ctx.close();
    }
}

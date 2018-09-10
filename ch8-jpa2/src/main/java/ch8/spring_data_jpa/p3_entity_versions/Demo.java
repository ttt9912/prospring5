package ch8.spring_data_jpa.p3_entity_versions;

import ch8.spring_data_jpa.p3_entity_versions.entities.SingerAudit;
import ch8.spring_data_jpa.p3_entity_versions.service.SingerAuditService;
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
        SingerAuditService singerAuditService = ctx.getBean(SingerAuditService.class);

        System.out.println("\n--- Insert new Singer ---");
        SingerAudit singer = new SingerAudit();
        singer.setFirstName("BB");
        singer.setLastName("King");
        singer.setBirthDate((Date.valueOf("1940-08-16")));
        singerAuditService.save(singer);

        System.out.println("\n--- Update Singer ---");
        SingerAudit existingSinger = singerAuditService.findById(4L);
        existingSinger.setFirstName("Riley B.");
        singerAuditService.save(existingSinger);

        System.out.println("\n--- SingerAudits ---");
        singerAuditService.findAll().forEach(System.out::println);


        System.out.println("\n--- History records with Revisions ---");

        SingerAudit oldSinger = singerAuditService.findByRevision(4l, 1);
        System.out.println("\nOld Singer with id=4 and revision=1:\n\t" + oldSinger);

        SingerAudit upToDateSinger = singerAuditService.findByRevision(4l, 2);
        System.out.println("\nUp to date Singer with id=4 and revision=2:\n\t" + upToDateSinger);

        ctx.close();
    }
}

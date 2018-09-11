package ch8.p2_spring_data_jpa.tracking_entity_changes;

import ch8.p2_spring_data_jpa.tracking_entity_changes.entities.SingerAudit;
import ch8.p2_spring_data_jpa.tracking_entity_changes.service.SingerAuditService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Date;


class Demo {

    @Test
    void demo() {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(DataJpaAuditConfig.class);
        SingerAuditService singerAuditService = ctx.getBean(SingerAuditService.class);

        System.out.println("\n--- SingerAudits ---");
        singerAuditService.findAll().forEach(System.out::println);


        System.out.println("\n--- Insert new Singer ---");
        final SingerAudit singer = new SingerAudit();
        singer.setFirstName("BB");
        singer.setLastName("King");
        singer.setBirthDate(Date.valueOf("1940-08-16"));
        singerAuditService.save(singer);

        System.out.println("\n--- Update Singer ---");
        SingerAudit existingSinger = singerAuditService.findById(4L);
        existingSinger.setFirstName("John Clayton");
        singerAuditService.save(existingSinger);
        SingerAudit updatedSinger = singerAuditService.findById(4L);
        System.out.println(updatedSinger);


        ctx.close();
    }
}

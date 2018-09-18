package ch9.p4_global_transactions_jta;

import ch9.entities.Singer;
import ch9.services.SingerService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Date;
import java.util.List;

/*
 * Global transaction: atomicity over multiple backend resources (all or none are updated)
 *
 * JTA: standard for implementing global transactions
 *
 * H2 dosen't fully support XA, therefore MySQL (docker) is used
 *
 * Atomikos: JTA transaction manager for non-JEE environment
 */
class Demo {

    @Test
    void main() {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(ServicesConfig.class, XAJpaConfig.class);

        SingerService singerService = ctx.getBean(SingerService.class);


        System.out.println("\n--- save to all DataSources ---");
        final Singer singer = new Singer();
        singer.setFirstName("John");
        singer.setLastName("Mayer");
        singer.setBirthDate(Date.valueOf("1977-09-16"));
        singerService.save(singer);

        if (singer.getId() != null) {
            System.out.println("Saved Singer with id = " + singer.getId());
        } else {
            System.out.println("Singer was not saved! Check Configuration.");
        }


        final List<Singer> singers = singerService.findAll();
        System.out.println("\n--- Singers from all DataSources ---");
        singers.forEach(System.out::println);

        ctx.close();
    }
}

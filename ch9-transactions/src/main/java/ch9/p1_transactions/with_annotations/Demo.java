package ch9.p1_transactions.with_annotations;

import ch9.config.DataJpaConfig;
import ch9.entities.Singer;
import ch9.services.SingerService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

class Demo {

    @Test
    void demo() {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(ServicesConfig.class, DataJpaConfig.class);

        SingerService singerService = ctx.getBean("singerService", SingerService.class);

        System.out.println("\n--- findById (readOnly Transaction) ---");
        final Singer singer = singerService.findById(1L);
        System.out.println(singer);

        System.out.println("\n--- save (default rw Transaction) ---");
        Singer savedSinger = singerService.save(singer);
        System.out.println(savedSinger);

        System.out.println("\n--- findAll (readOnly Transaction) ---");
        final List<Singer> singers = singerService.findAll();
        singers.forEach(System.out::println);

        System.out.println("\n--- count (non-transactional) ---");
        long count = singerService.countAll();
        System.out.println("number of Singers: " + count);

        ctx.close();
    }
}

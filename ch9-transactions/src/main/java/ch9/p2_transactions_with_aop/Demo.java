package ch9.p2_transactions_with_aop;

import ch9.entities.Singer;
import ch9.services.SingerService;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;

class Demo {

    @Test
    void demo() {
        GenericXmlApplicationContext ctx =
                new GenericXmlApplicationContext();
        ctx.load("tx_aop_context.xml");
        ctx.refresh();

        final SingerService singerService = ctx.getBean("singerServiceAop", SingerService.class);

        System.out.println("\n--- findAll (readOnly) ---");
        final List<Singer> singers = singerService.findAll();
        singers.forEach(System.out::println);

        System.out.println("\n--- count (non-transactional) ---");
        long count = singerService.countAll();
        System.out.println("Singer Count: " + count);

        System.out.println("\n--- save ---");
        final Singer singer = singerService.findById(1L);
        singer.setFirstName("John Clayton");
        singerService.save(singer);

        ctx.close();
    }
}

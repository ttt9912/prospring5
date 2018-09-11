package ch8.p3_springboot;

import ch8.entity.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.List;

/*
 * scanBasePackages: beans within are created and added to the application context.
 *                   not necessary here, because they are all in the same package
 *                   as the Application class.
 *
 * - No other configuration is needed.
 * - No SQL scripts for DB initialization needed-
 */
@SpringBootApplication(scanBasePackages = "ch8.p3_springboot")
@EntityScan(basePackages = {"ch8.entity"})
class Application implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(DbInitializer.class);

    @Autowired
    private SingerRepository singerRepository;


    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext ctx =
                SpringApplication.run(Application.class, args);

        System.in.read();
        ctx.close();
    }


    @Override
    public void run(final String... args) {
        System.out.println("\n--- Singers ---");
        final Iterable<Singer> singers = singerRepository.findAll();
        singers.forEach(System.out::println);

        System.out.println("\n--- Singers by firstName ---");
        final List<Singer> singersByFirstName = singerRepository.findByFirstName("John");
        singersByFirstName.forEach(System.out::println);
    }
}

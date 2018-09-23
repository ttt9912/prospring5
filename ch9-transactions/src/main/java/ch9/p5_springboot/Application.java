package ch9.p5_springboot;

import ch9.entities.Singer;
import ch9.services.SingerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.sql.Date;

/*
 * Spring Boot JTA contains a libary for Atomikos
 *
 * DOES NOT WORK!!! (Error in getting XA resource)
 */
@SpringBootApplication(scanBasePackages = "ch9.p5_springboot")
@EntityScan(basePackages = {"ch9.entity"})
class Application implements CommandLineRunner {
    private static Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private SingerService singerService;

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext ctx =
                SpringApplication.run(Application.class, args);

        System.in.read();
        ctx.close();
    }


    @Override
    public void run(final String... args) throws Exception {
        final Singer singer = new Singer();
        singer.setFirstName("John");
        singer.setLastName("Mayer");
        singer.setBirthDate(Date.valueOf("1970-09-16"));
        singerService.save(singer);

        long count = singerService.countAll();
        if (count == 1) {
            logger.info("Singer saved successfully");
        } else {
            logger.error("Singer was not saved, check configuration!");
        }
    }
}

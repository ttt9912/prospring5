package ch6.p4_spring_boot_jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
class Application implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private SingerDAO singerDAO;

    public static void main(String... args) throws IOException {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(final String... args) throws Exception {
        String singerName = singerDAO.findNameById(1L);
        System.out.println("Singer.firstName for id=1: " + singerName);
    }
}
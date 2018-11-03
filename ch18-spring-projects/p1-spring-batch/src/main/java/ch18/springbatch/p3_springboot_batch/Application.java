package ch18.springbatch.p3_springboot_batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "ch18.springbatch.p3_springboot_batch")
@EntityScan(basePackages = "ch18.springbatch.p3_springboot_batch")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

package ch11.p1_simple_task;

import ch11.entity.Car;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

class Demo {

    @Test
    void demo() {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(AppConfig.class);

        CarServiceImpl carService = ctx.getBean("carService", CarServiceImpl.class);

        System.out.println("\n--- cars ---");
        List<Car> cars = carService.findAll();
        cars.forEach(System.out::println);

        ctx.close();
    }
}

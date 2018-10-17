package ch14.p2_groovy_spring;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.io.IOException;
import java.time.LocalDate;

/*
 * domain object:       /src/main/java/
 * rule definition:     /src/main/groovy
 * rule implementation: /rules/             (spring refreshable bean)
 *
 * Rule is applied once. Spring checks for changes in RuleFactoryImpl file.
 * If changes: rule will be applied again (Dynamic Refreshable bean).
 */
public class Demo {

    public static void main(String... args) throws IOException {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("spring-groovy-context.xml");
        ctx.refresh();

        SingerService singerService = ctx.getBean("singerService", SingerService.class);

        Singer singer = new Singer();
        singer.setId(1l);
        singer.setFirstName("John");
        singer.setLastName("Mayer");
        singer.setBirthDate(LocalDate.of(1977, 10, 16));

        singerService.applyRule(singer);
        System.out.println("Singer " + singer);

        // waits here -> modify RuleFactoryImpl and rule will be applied again
        System.in.read(); // (make user input to get pass this point)

        singerService.applyRule(singer);
        System.out.println("Singer " + singer);

        ctx.close();
    }
}

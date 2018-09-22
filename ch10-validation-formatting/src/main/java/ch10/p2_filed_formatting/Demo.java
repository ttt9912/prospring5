package ch10.p2_filed_formatting;

import ch10.Singer;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.convert.ConversionService;

class Demo {

    @Test
    void demo() {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(AppConfig.class);

        Singer singer = ctx.getBean("john", Singer.class);
        System.out.println("Singer: " + singer);

        ConversionService conversionService = ctx.getBean("conversionService", ConversionService.class);
        String birthDate = conversionService.convert(singer.getBirthDate(), String.class);
        System.out.println(birthDate);

        ctx.close();
    }
}

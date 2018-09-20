package ch10.p1_type_conversion.with_type_conversion;

import ch10.p1_type_conversion.AnotherSinger;
import ch10.p1_type_conversion.Singer;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.convert.ConversionService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * Type Conversion
 *
 * convert any type to POJO
 */
class Demo {

    @Test
    void custom_converter() {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(AppConfig.class);

        Singer john = ctx.getBean("john", Singer.class);
        System.out.println(john);

        ctx.close();
    }

    @Test
    void converting_arbitrary_types() {
        // convert any instance of Singer to AnotherSinger with firstName and lastName swapped
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(AppConfig.class);

        ConversionService conversionService = ctx.getBean(ConversionService.class);

        // conversion with custom converter (SingerToAnotherSingerConverter)
        Singer singer = ctx.getBean("john", Singer.class);
        AnotherSinger anotherSinger = conversionService.convert(singer, AnotherSinger.class);
        System.out.println(anotherSinger);

        ctx.close();

    }


    /*
     * conversion that are already supported by ConversionService
     *
     * built in converters: StringToArrayConverter usw.
     * under: org.springframework.core.convert.support
     */
    @Test
    void built_in_converters() {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(AppConfig.class);

        ConversionService conversionService = ctx.getBean(ConversionService.class);

        System.out.println("\n--- vararg to String[] ---");
        final String[] array = conversionService.convert("a,b,c", String[].class);
        System.out.println("String array: " + array[0] + array[1] + array[2]);


        System.out.println("\n--- List to HashSet ---");
        final List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        final Set<String> set = conversionService.convert(list, HashSet.class);
        set.forEach(s -> System.out.println("Set: " + s));

        ctx.close();
    }
}

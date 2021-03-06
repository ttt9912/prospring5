package ch8.p2_spring_data_jpa.simple_queries;

import ch8.entity.Album;
import ch8.entity.Singer;
import ch8.p2_spring_data_jpa.simple_queries.service.AlbumService;
import ch8.p2_spring_data_jpa.simple_queries.service.SingerService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

class Demo {

    @Test
    void simple_data_jpa_inferred_queries() {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(DataJpaConfig.class);
        SingerService singerService = ctx.getBean(SingerService.class);

        System.out.println("\n--- Singers ---");
        List<Singer> singers = singerService.findAll();
        singers.forEach(System.out::println);

        System.out.println("\n--- Singers findByFirstName ---");
        List<Singer> byFirstName = singerService.findByFirstName("John");
        byFirstName.forEach(System.out::println);

        System.out.println("\n--- Singers findByFirstNameAndLastName ---");
        List<Singer> byFNAndLN = singerService.findByFirstNameAndLastName("John", "Mayer");
        byFNAndLN.forEach(System.out::println);

        ctx.close();
    }

    @Test
    void custom_queries() {
        // custom query cannot be inferred, they must be defined using @Query

        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(DataJpaConfig.class);
        AlbumService albumService = ctx.getBean(AlbumService.class);

        System.out.println("\n--- Albums with title containing 'The' ---");
        List<Album> albums = albumService.findByTitleLike("The");
        albums.forEach(System.out::println);

        ctx.close();
    }

}

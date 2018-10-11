package ch12.springboot.rest.app;

import ch12.springboot.rest.data.Singer;
import ch12.springboot.rest.service.SingerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ContextTest {


    @Autowired
    private SingerService singerService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void findAll() {
        System.out.println("--- Singers ---");
        List<Singer> singers = singerService.findAll();
        singers.forEach(System.out::println);
    }
}
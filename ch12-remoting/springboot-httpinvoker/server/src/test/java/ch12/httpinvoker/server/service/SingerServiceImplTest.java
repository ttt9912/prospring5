package ch12.httpinvoker.server.service;

import ch12.httpinvoker.api.element.SingerApiElement;
import ch12.httpinvoker.api.service.SingerService;
import ch12.httpinvoker.server.app.ServerApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServerApp.class)
public class SingerServiceImplTest {

    @Autowired
    private SingerService singerService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void findAll() {
        List<SingerApiElement> singers = singerService.findAll();
        singers.forEach(System.out::println);
    }


}
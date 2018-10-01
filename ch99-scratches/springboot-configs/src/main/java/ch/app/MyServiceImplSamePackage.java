package ch.app;

import ch.service.MyService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service("myServiceSamePackage")
public class MyServiceImplSamePackage implements MyService {

    @PostConstruct
    public void postConstruct() {
        System.out.println(this.getClass().getSimpleName() + "initialized");
    }
}

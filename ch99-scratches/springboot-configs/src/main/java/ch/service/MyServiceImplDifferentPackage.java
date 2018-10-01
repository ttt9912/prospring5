package ch.service;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service("myServiceImplDifferentPackage")
public class MyServiceImplDifferentPackage implements MyService {

    @PostConstruct
    public void postConstruct() {
        System.out.println(this.getClass().getSimpleName() + "initialized");
    }
}

package ch4.p12_springboot.web_app;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class HelloWorldController {

    @RequestMapping("/")
    public String sayHi(){
        return "Hello World";
    }
}

package ch4.p12_springboot.boot_app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
class HelloWorld {

    private static Logger logger = LoggerFactory.getLogger(HelloWorld.class);

    void sayHi(){
        logger.info("Hello World!");
    }
}

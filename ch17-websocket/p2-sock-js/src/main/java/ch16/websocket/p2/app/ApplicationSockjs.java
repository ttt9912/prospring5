package ch16.websocket.p2.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/*
 * SockJS: fallback behaviour if browser does not support Websocket
 *
 * @EnableAsync: required for SockJS,
 *               enables async messaging
 *
 * SockJS can be tested by disabling Websocket in the browser
 */
@SpringBootApplication
@EnableAsync
public class ApplicationSockjs {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationSockjs.class, args);
    }
}

package ch18.reactive.httproutes;

import ch18.reactive.httproutes.web.SingerHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ServletHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/*
 * Defines the Routing between web request and Handler
 * (unlike when using @Controllers)
 *
 * Exposes HttpHandler to defined routes: localhost:8080/singers etc.
 */
@SpringBootApplication
public class Application {

    @Autowired
    private SingerHandler singerHandler;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // this behaviour resembles @RequestMapping
    private RouterFunction<ServerResponse> routingFunctions() {
        return route(GET("/test"), serverRequest -> ok().body(fromObject("works!")))
                .andRoute(GET("/singers"), singerHandler::list)
                .andRoute(GET("/singers/{id}"), singerHandler::show)
                .andRoute(POST("/singers/{id}"), singerHandler::save)
                .filter((request, next) -> next.handle(request));
    }

    @Bean
    ServletRegistrationBean servletRegistrationBean() {
        HttpHandler httpHandler = RouterFunctions.toHttpHandler(routingFunctions());

        ServletRegistrationBean<ServletHttpHandlerAdapter> registrationBean =
                new ServletRegistrationBean<>(new ServletHttpHandlerAdapter(httpHandler), "/");
        registrationBean.setLoadOnStartup(1);
        registrationBean.setAsyncSupported(true);
        return registrationBean;
    }
}

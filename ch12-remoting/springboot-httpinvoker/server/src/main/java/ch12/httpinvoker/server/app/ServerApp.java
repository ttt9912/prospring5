package ch12.httpinvoker.server.app;

import ch12.httpinvoker.api.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

@SpringBootApplication
public class ServerApp {

    @Autowired
    private SingerService singerService;

    // export the service implementation under
    // localhost:<server.port>/singerService
    @Bean(name = "/singerService")
    HttpInvokerServiceExporter singerService() {
        HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
        exporter.setService(singerService);
        exporter.setServiceInterface(SingerService.class);
        return exporter;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(ServerApp.class, args);
        // ctx.close();

        // Context Tests unter src/test/
    }
}

package app;

import ch12.config.DataConfig;
import ch12.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

@Configuration
@Import(DataConfig.class)
@ComponentScan
@EnableAutoConfiguration
public class Server {

    @Autowired
    private SingerService singerService;

    @Bean(name = "/httpInvoker/singerService")
    HttpInvokerServiceExporter accountService() {
        HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
        exporter.setService(singerService);
        exporter.setServiceInterface(SingerService.class);
        return exporter;
    }

    public static void main(String[] args) {
        SpringApplication.run(Server.class, args);
    }
}

package ch18.integration;

import ch18.integration.config.IntegrationConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

/*
 * Integration waits for files and starts batch job when a file is pasted
 * in the directory '/Users/ttt/dev/workspaces/prospring5/file/temp/'
 *
 * -> Start app and then place a csv file with persons under
 *      '/Users/ttt/dev/workspaces/prospring5/file/temp/'
 */
public class Demo {
    private static Logger logger = LoggerFactory.getLogger(Demo.class);

    public static void main(String... args) throws Exception {
        GenericApplicationContext ctx =
                new AnnotationConfigApplicationContext(IntegrationConfig.class);

        System.in.read();
        ctx.close();
    }
}

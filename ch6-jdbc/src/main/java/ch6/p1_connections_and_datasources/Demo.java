package ch6.p1_connections_and_datasources;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;

class Demo {

    private static Logger logger = LoggerFactory.getLogger(Demo.class);

    // ---------------------------------------------------------------------------
    // Connect to a remote Mysql (run in a Docker Container)
    // ---------------------------------------------------------------------------

    @Test
    void mysqlDocker_withContextXml() {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("connect_mysql_context.xml");
        ctx.refresh();

        DataSource dataSource = ctx.getBean("dataSource", DataSource.class);
        assertNotNull(dataSource);
        testDataSource(dataSource);

        ctx.close();
    }

    @Test
    void mysqlDocker_withConfiguration() {
        GenericApplicationContext ctx =
                new AnnotationConfigApplicationContext(MysqlDBConfig.class);

        DataSource dataSource = ctx.getBean("dataSource", DataSource.class);
        assertNotNull(dataSource);
        testDataSource(dataSource);

        ctx.close();
    }

    // ---------------------------------------------------------------------------
    // Connect to embedded H2
    // ---------------------------------------------------------------------------

    @Test
    void embeddedH2_withContextXml() {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("connect_h2_context.xml");
        ctx.refresh();

        DataSource dataSource = ctx.getBean("dataSource", DataSource.class);
        assertNotNull(dataSource);
        testDataSource(dataSource);

        ctx.close();
    }

    @Test
    void embeddedH2_withConfiguration() {
        GenericApplicationContext ctx =
                new AnnotationConfigApplicationContext(EmbeddedH2Config.class);

        DataSource dataSource = ctx.getBean("dataSource", DataSource.class);
        assertNotNull(dataSource);
        testDataSource(dataSource);

        ctx.close();
    }


    private void testDataSource(final DataSource dataSource) {
        Connection connection = null;

        try {
            connection = dataSource.getConnection();

            PreparedStatement statement = connection.prepareStatement("select * from singer");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String val = resultSet.getString("first_name");
                logger.info(val);
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

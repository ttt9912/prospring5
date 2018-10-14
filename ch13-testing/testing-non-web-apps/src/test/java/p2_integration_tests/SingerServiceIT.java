package p2_integration_tests;

import ch13.config.DataSourceConfig;
import ch13.config.ServiceConfig;
import ch13.entities.Singer;
import ch13.service.SingerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 * Use of JUnit5 Jupiter
 *
 * @SpringJUnitConfig: create ApplicationContext
 *                     combines junit5s @ExtendWith with
 *                     springs @ContextConfiguration
 *
 * @Sql: define sql scripts to be executed before or after test method
 */
@SpringJUnitConfig(classes = {SimpleTestConfig.class, ServiceConfig.class, DataSourceConfig.class})
@DisplayName("SingerService integration test")
@ActiveProfiles("test")
public class SingerServiceIT {

    @Autowired
    private SingerService singerService;

    @Test
    @DisplayName("should return all singers")
    @SqlGroup({
            @Sql(value = "classpath:sql/test-data.sql",
                    config = @SqlConfig(encoding = "utf-8", separator = ";", commentPrefix = "--"),
                    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(value = "classpath:sql/test-data.sql",
                    config = @SqlConfig(encoding = "utf-8", separator = ";", commentPrefix = "--"),
                    executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    })
    public void findSingers() {
        List<Singer> singers = singerService.findAll();
        assertEquals(1, singers.size());

        List<Singer> pauls = singerService.findByFirstName("Paul");
        assertEquals(1, pauls.size());
    }
}

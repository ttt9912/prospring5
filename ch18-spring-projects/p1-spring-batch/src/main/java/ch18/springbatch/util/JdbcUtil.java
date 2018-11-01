package ch18.springbatch.util;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;

public class JdbcUtil {

    private JdbcTemplate jdbcTemplate;
    private RowMapper<String> stringRowMapper;

    public JdbcUtil(final DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        stringRowMapper = (rs, i) -> rs.getString(1) + " " +
                rs.getString(2) + " " + rs.getString(3);
    }

    public List executeQuery(String query) {
        return jdbcTemplate.query(query, stringRowMapper);
    }

}

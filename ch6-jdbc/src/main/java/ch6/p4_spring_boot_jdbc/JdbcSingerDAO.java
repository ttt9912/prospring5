package ch6.p4_spring_boot_jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcSingerDAO implements SingerDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String findNameById(final Long id) {
        final String sql = "SELECT first_name || ' ' || last_name FROM singer WHERE id = ?";
        final Object[] parameters = {id};

        return jdbcTemplate.queryForObject(sql, parameters, String.class);
    }
}

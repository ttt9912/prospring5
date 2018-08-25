package ch6.p2_jdbcTemplate;

import ch6.entity.Singer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

class SingerRowMapper implements RowMapper<Singer> {

    @Override
    public Singer mapRow(final ResultSet resultSet, final int i) throws SQLException {
        final Singer singer = new Singer();
        singer.setId(resultSet.getLong("id"));
        singer.setFirstName(resultSet.getString("first_name"));
        singer.setLastName(resultSet.getString("last_name"));
        singer.setBirthDate(resultSet.getDate("birth_date"));
        return singer;
    }
}

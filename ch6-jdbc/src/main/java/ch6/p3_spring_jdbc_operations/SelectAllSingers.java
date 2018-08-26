package ch6.p3_spring_jdbc_operations;

import ch6.entity.Singer;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * MappingSqlQuery<?>: Execute Query & map ResultSet to entity
 *
 * mapRow(): map each ResultSet row into the corresponding domain object
 */
class SelectAllSingers extends MappingSqlQuery<Singer> {

    private static final String SQL_SELECT_ALL_SINGER =
            "SELECT id, first_name, last_name, birth_date FROM singer";

    SelectAllSingers(final DataSource dataSource) {
        super(dataSource, SQL_SELECT_ALL_SINGER);
    }

    @Override
    protected Singer mapRow(final ResultSet resultSet, final int i) throws SQLException {
        final Singer singer = new Singer();
        singer.setId(resultSet.getLong("id"));
        singer.setFirstName(resultSet.getString("first_name"));
        singer.setLastName(resultSet.getString("last_name"));
        singer.setBirthDate(resultSet.getDate("birth_date"));
        return singer;
    }
}

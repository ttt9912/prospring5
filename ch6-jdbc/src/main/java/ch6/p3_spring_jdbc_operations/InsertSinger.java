package ch6.p3_spring_jdbc_operations;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/*
 * Primary key is generated by the RDBMS (AUTO_INCREMENT)
 *
 * setReturnGeneratedKeys(true): returns generated key to jdbc
 * so that it can be retrieved and mapped to the domain object
 */
class InsertSinger extends SqlUpdate {

    private static final String SQL_INSERT_SINGER =
            "INSERT INTO singer (first_name, last_name, birth_date)" +
                    "VALUES (:firstName, :lastName, :birthDate)";

    InsertSinger(final DataSource dataSource) {
        super(dataSource, SQL_INSERT_SINGER);
        super.declareParameter(new SqlParameter("firstName", Types.VARCHAR));
        super.declareParameter(new SqlParameter("lastName", Types.VARCHAR));
        super.declareParameter(new SqlParameter("birthDate", Types.DATE));

        // declare PK column(s)
        super.setGeneratedKeysColumnNames("id");

        // return generated key to jdbc
        super.setReturnGeneratedKeys(true);
    }
}

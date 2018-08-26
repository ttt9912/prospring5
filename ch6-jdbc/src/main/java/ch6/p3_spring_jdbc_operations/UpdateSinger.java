package ch6.p3_spring_jdbc_operations;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

class UpdateSinger extends SqlUpdate {

    private static final String SQL_UPDATE_SINGER =
            "UPDATE singer " +
                    "SET first_name = :firstName, last_name = :lastName, birth_date = :birthDate " +
                    "WHERE id = :id";

    UpdateSinger(final DataSource dataSource) {
        super(dataSource, SQL_UPDATE_SINGER);
        super.declareParameter(new SqlParameter("firstName", Types.VARCHAR));
        super.declareParameter(new SqlParameter("lastName", Types.VARCHAR));
        super.declareParameter(new SqlParameter("birthDate", Types.DATE));
        super.declareParameter(new SqlParameter("id", Types.INTEGER));
    }
}

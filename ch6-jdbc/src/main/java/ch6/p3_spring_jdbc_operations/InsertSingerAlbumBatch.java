package ch6.p3_spring_jdbc_operations;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.BatchSqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

class InsertSingerAlbumBatch extends BatchSqlUpdate {

    private static final String SQL_INSERT_SINGER_ALBUM =
            "INSERT INTO album (singer_id, title, release_date)" +
                    "VALUES (:singerId, :title, :releaseDate)";

    private static final int batchSize = 10;


    InsertSingerAlbumBatch(final DataSource dataSource) {
        super(dataSource, SQL_INSERT_SINGER_ALBUM);
        super.declareParameter(new SqlParameter("singerId", Types.INTEGER));
        super.declareParameter(new SqlParameter("title", Types.VARCHAR));
        super.declareParameter(new SqlParameter("releaseDate", Types.DATE));

        setBatchSize(batchSize);
    }
}

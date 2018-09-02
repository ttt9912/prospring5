package ch6.p3_spring_jdbc_operations;

import ch6.entity.Album;
import ch6.entity.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;

/*
 * @Resource dataSource is set by spring. No need to call the setter in the Configuration.
 */
@Repository("singerDAO")
class JdbcSingerDAO implements SingerDAO {
    private static Logger logger = LoggerFactory.getLogger(JdbcSingerDAO.class);

    private DataSource dataSource;

    // MappingSqlQuery<>
    private SelectAllSingers selectAllSingers;
    private SelectSingersByFirstName selectSingersByFirstName;

    // SqlUpdate
    private UpdateSinger updateSinger;
    private InsertSinger insertSinger;

    // BatchSqlUpdate
    private InsertSingerAlbumBatch insertSingerAlbumBatch;


    @Resource(name = "dataSource")
    public void setDataSource(final DataSource dataSource) {
        this.dataSource = dataSource;
        this.selectAllSingers = new SelectAllSingers(dataSource);
        this.selectSingersByFirstName = new SelectSingersByFirstName(dataSource);
        this.updateSinger = new UpdateSinger(dataSource);
        this.insertSinger = new InsertSinger(dataSource);
        this.insertSingerAlbumBatch = new InsertSingerAlbumBatch(dataSource);
    }

    // ---------------------------------------------------------------------------
    // Implemented with MappingSqlQuery<?>
    // ---------------------------------------------------------------------------

    @Override
    public List<Singer> findAll() {
        return selectAllSingers.execute();
    }

    @Override
    public List<Singer> findByFirstName(final String firstName) {
        final HashMap<String, Object> params = new HashMap<>();
        params.put("firstName", firstName);
        return selectSingersByFirstName.executeByNamedParam(params);
    }


    // ---------------------------------------------------------------------------
    // Implemented with SqlUpdate
    // ---------------------------------------------------------------------------

    @Override
    public void update(final Singer singer) {
        final HashMap<String, Object> params = new HashMap<>();
        params.put("firstName", singer.getFirstName());
        params.put("lastName", singer.getLastName());
        params.put("birthDate", singer.getBirthDate());
        params.put("id", singer.getId());
        updateSinger.updateByNamedParam(params);
        logger.info("Updated Singer with id {}", singer.getId());
    }

    @Override
    public void insert(final Singer singer) {
        final HashMap<String, Object> params = new HashMap<>();
        params.put("firstName", singer.getFirstName());
        params.put("lastName", singer.getLastName());
        params.put("birthDate", singer.getBirthDate());
        final GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        insertSinger.updateByNamedParam(params, keyHolder);
        // get key (generated by RDBMS' insert operation)
        singer.setId(keyHolder.getKey().longValue());
        logger.info("New Singer created with id {}", singer.getId());
    }

    @Override
    public void insertWithAlbum(final Singer singer) {

        // insert singer normal
        final HashMap<String, Object> singerParams = new HashMap<>();
        singerParams.put("firstName", singer.getFirstName());
        singerParams.put("lastName", singer.getLastName());
        singerParams.put("birthDate", singer.getBirthDate());
        final GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        insertSinger.updateByNamedParam(singerParams, keyHolder);
        singer.setId(keyHolder.getKey().longValue());
        logger.info("New Singer created with id {}", singer.getId());

        // insert albums with batch
        final List<Album> albums = singer.getAlbums();
        if (albums != null) {
            for (Album album : albums) {
                final HashMap<String, Object> albumParams = new HashMap<>();
                albumParams.put("singerId", singer.getId());
                albumParams.put("title", album.getTitle());
                albumParams.put("releaseDate", album.getReleaseDate());
                insertSingerAlbumBatch.updateByNamedParam(albumParams);
            }
        }
        // execute batch if batch size is not reached
        insertSingerAlbumBatch.flush();
    }

}

package ch6.p2_jdbcTemplate;

import ch6.entity.Singer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * JdbcTemplate:
 *      - can issue any type of SQL statement to the DB
 *      - includes the DataSource
 *      - is thread save, can be injected in multiple DAO classes
 *
 * RowMapper: row mapping to domain objects
 */
class JdbcSingerDAO implements SingerDAO {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    // simple query
    @Override
    public String findNameById(final Long id) {
        final String sql = "SELECT first_name || ' ' || last_name FROM singer WHERE id = ?";
        final Object[] parameters = {id};

        return jdbcTemplate.queryForObject(sql, parameters, String.class);
    }


    // named parameters: use NamedParameterJdbcTemplate
    @Override
    public String findFirstNameById(final Long id) {
        final NamedParameterJdbcTemplate namedParamJdbcTemplate
                = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());

        final String sql = "SELECT first_name FROM singer WHERE id = :singerId";
        final Map<String, Object> namedParams = new HashMap<>();
        namedParams.put("singerId", id);

        return namedParamJdbcTemplate.queryForObject(sql, namedParams, String.class);
    }

    // Use RowMapper<?> to map Resultset to POJO
    @Override
    public List<Singer> findAll() {
        final String sql = "SELECT id, first_name, last_name, birth_date FROM singer";
        return jdbcTemplate.query(sql, new SingerRowMapper());
    }

    // Use a RowMapper<?> to map Relationships
    @Override
    public List<Singer> findAllWithAlbums() {
        final String sql = "" +
                "SELECT s.id, s.first_name, s.last_name, s.birth_date, " +
                "a.id AS album_id, a.title, a.release_date " +
                "FROM singer s LEFT JOIN album a " +
                "ON s.id = a.singer_id";
        return jdbcTemplate.query(sql, new SingerWithAlbumsExtractor());
    }


    @Override
    public void insert(final Singer singer) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public void update(final Singer singer) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public void delete(final Singer singer) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public void insertWithDetail(final Singer singer) {
        throw new UnsupportedOperationException("not implemented");
    }
}

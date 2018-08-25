package ch6.p2_jdbcTemplate;

import ch6.entity.Album;
import ch6.entity.Singer;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class SingerWithAlbumsExtractor implements ResultSetExtractor<List<Singer>> {

    @Override
    public List<Singer> extractData(final ResultSet resultSet) throws SQLException, DataAccessException {

        final HashMap<Long, Singer> map = new HashMap<>();
        Singer currentSinger = null;


        while (resultSet.next()) {
            Long singerId = resultSet.getLong("id");

            if (map.get(singerId) == null) {
                currentSinger = new Singer();
                currentSinger.setId(resultSet.getLong("id"));
                currentSinger.setFirstName(resultSet.getString("first_name"));
                currentSinger.setLastName(resultSet.getString("last_name"));
                currentSinger.setBirthDate(resultSet.getDate("birth_date"));
                currentSinger.setAlbums(new ArrayList<>());
                map.put(singerId, currentSinger);
            }


            long albumId = resultSet.getLong("album_id");

            if (albumId > 0) {
                final Album album = new Album();
                album.setId(albumId);
                album.setSingerId(singerId);
                album.setTitle(resultSet.getString("title"));
                album.setReleaseDate(resultSet.getDate("release_date"));
                currentSinger.getAlbums().add(album);
            }
        }

        return new ArrayList<>(map.values());
    }
}

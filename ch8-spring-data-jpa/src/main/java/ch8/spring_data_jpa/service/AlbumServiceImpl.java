package ch8.spring_data_jpa.service;

import ch8.spring_data_jpa.entity.Album;
import ch8.spring_data_jpa.entity.Singer;
import ch8.spring_data_jpa.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("springJpaAlbumService")
@Transactional
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Transactional(readOnly = true)
    public List<Album> findBySinger(final Singer singer) {
        return albumRepository.findBySinger(singer);
    }

    @Transactional(readOnly = true)
    public List<Album> findByTitleLike(final String title) {
        return albumRepository.findByTitleLike(title);
    }
}

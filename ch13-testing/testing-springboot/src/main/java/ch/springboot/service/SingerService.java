package ch.springboot.service;

import ch.springboot.data.Singer;
import ch.springboot.data.SingerRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("singerService")
@Transactional
public class SingerService {

    @Autowired
    private SingerRepository singerRepository;

    @Transactional(readOnly = true)
    public List<Singer> findAll() {
        return Lists.newArrayList(singerRepository.findAll());
    }

    @Transactional(readOnly = true)
    public List<Singer> findByFirstName(String firstName) {
        return singerRepository.findByFirstName(firstName);
    }

    @Transactional(readOnly = true)
    public Singer findById(Long id) {
        return singerRepository.findById(id).get();
    }

    public Singer save(Singer singer) {
        return singerRepository.save(singer);
    }

    public void delete(Singer singer) {
        singerRepository.delete(singer);
    }
}

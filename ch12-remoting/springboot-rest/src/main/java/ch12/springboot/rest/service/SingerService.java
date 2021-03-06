package ch12.springboot.rest.service;

import ch12.springboot.rest.data.Singer;
import ch12.springboot.rest.data.SingerRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("singerService")
@Transactional
public class SingerService {

    @Autowired
    private SingerRepository singerRepository;

    @Autowired
    private ConversionService conversionService;

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

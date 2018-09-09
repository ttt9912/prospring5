package ch8.spring_data_jpa.p1_queries.service;

import ch8.spring_data_jpa.p1_queries.entity.Singer;
import ch8.spring_data_jpa.p1_queries.repository.SingerRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
 * Instead of EntityManager, only the Repository bean needs to be injected.
 */
@Service("springJpaSingerService")
@Transactional
public class SingerServiceImpl implements SingerService {

    @Autowired
    private SingerRepository singerRepository;

    @Transactional(readOnly = true)
    public List<Singer> findAll() {
        return Lists.newArrayList(singerRepository.findAll());
    }

    @Transactional(readOnly = true)
    public List<Singer> findByFirstName(final String firstName) {
        return singerRepository.findByFirstName(firstName);
    }

    @Transactional(readOnly = true)
    public List<Singer> findByFirstNameAndLastName(final String firstName, final String lastName) {
        return singerRepository.findByFirstNameAndLastName(firstName, lastName);
    }
}

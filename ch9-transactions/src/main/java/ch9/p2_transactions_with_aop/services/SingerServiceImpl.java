package ch9.p2_transactions_with_aop.services;

import ch9.entities.Singer;
import ch9.repository.SingerRepository;
import ch9.services.SingerService;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * No @Transactional annotations
 *
 * Transaction management is configured with aop in context xml
 */
@Service("singerServiceAop")
class SingerServiceImpl implements SingerService {
    private static final Logger logger = LoggerFactory.getLogger(SingerServiceImpl.class);

    @Autowired
    private SingerRepository singerRepository;

    @Override
    public List<Singer> findAll() {
        logger.info("Find all singers");
        return Lists.newArrayList(singerRepository.findAll());
    }

    @Override
    public Singer findById(Long id) {
        return singerRepository.findById(id).get();
    }

    @Override
    public Singer save(Singer singer) {
        logger.info("Saving singer");
        return singerRepository.save(singer);
    }

    @Override
    public long countAll() {
        return singerRepository.countAllSingers();
    }
}
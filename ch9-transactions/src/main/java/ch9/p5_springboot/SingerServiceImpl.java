package ch9.p5_springboot;

import ch9.entities.Singer;
import ch9.repository.SingerRepository;
import ch9.services.SingerService;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

/*
 * At creation of a new Singer, transmit a message to a messaging queue
 */
@Service("singerService")
@Transactional
class SingerServiceImpl implements SingerService {

    private SingerRepository singerRepository;
    private JmsTemplate jmsTemplate;

    // no @Autowired needed.
    // Springboot injects the required beans, if they are the only ones declared
    public SingerServiceImpl(final SingerRepository singerRepository, final JmsTemplate jmsTemplate) {
        this.singerRepository = singerRepository;
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public Singer save(final Singer singer) {

        jmsTemplate.convertAndSend("singers", "just saved:" + singer);

        if (singer == null) {
            throw new RuntimeException("Singer cannot be null");
        }

        singerRepository.save(singer);
        return singer;
    }

    @Override
    public long countAll() {
        return singerRepository.countAllSingers();
    }

    @Override
    public List<Singer> findAll() {
        throw new NotImplementedException();
    }

    @Override
    public Singer findById(final Long id) {
        throw new NotImplementedException();
    }
}

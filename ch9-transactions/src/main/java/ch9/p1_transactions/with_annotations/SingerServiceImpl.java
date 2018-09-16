package ch9.p1_transactions.with_annotations;

import ch9.entities.Singer;
import ch9.repository.SingerRepository;
import ch9.services.SingerService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
 * 'readOnly = true' should generally be applied to all finder-methods
 *
 * - finder-methods, that return managed entities: readOnly
 * - save/update methods: readWrite (= default)
 * - finder-methods, that do not return managed entities (i.e. count()): non-transactional (Propagation.NEVER)
 */
@Service("singerService")
@Transactional
        // ensures, a transaction is present before each method
class SingerServiceImpl implements SingerService {

    private SingerRepository singerRepository;

    @Autowired
    public void setSingerRepository(final SingerRepository singerRepository) {
        this.singerRepository = singerRepository;
    }

    @Override
    @Transactional(readOnly = true) // override class level transaction properties
    public List<Singer> findAll() {
        return Lists.newArrayList(singerRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Singer findById(final Long id) {
        return singerRepository.findById(id).get();
    }

    @Override
    public Singer save(final Singer singer) {
        return singerRepository.save(singer);
    }

    @Override
    @Transactional(propagation = Propagation.NEVER)
    public long countAll() {
        return singerRepository.count();
    }
}

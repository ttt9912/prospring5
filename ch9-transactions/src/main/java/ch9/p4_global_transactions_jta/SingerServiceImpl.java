package ch9.p4_global_transactions_jta;

import ch9.entities.Singer;
import ch9.services.SingerService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/*
 * Atomikos
 * - creates a composite transaction
 * - communicates with the XA DataSource (MySql)
 * - performs synchronization
 * - commits the transaction
 */
@Service("singerService")
@Repository
@Transactional
public class SingerServiceImpl implements SingerService {

    private static final String FIND_ALL = "select s from Singer s";

    @PersistenceContext(unitName = "emfA")
    private EntityManager emA;

    @PersistenceContext(unitName = "emfB")
    private EntityManager emB;


    @Override
    @Transactional(readOnly = true)
    public List<Singer> findAll() {
        final List<Singer> singersFromA = findAllInA();
        final List<Singer> singersFromB = findAllInB();

        if (singersFromA.size() != singersFromB.size()) {
            throw new AsyncXAResourcesException("XA resources do not contain the same expected data.");
        }

        final List<Singer> singersFromBoth = new ArrayList<>();
        singersFromBoth.addAll(singersFromA);
        singersFromBoth.addAll(singersFromB);
        return singersFromBoth;
    }

    @Override
    public Singer findById(final Long id) {
        throw new NotImplementedException();
    }

    // persist the same object to two databases
    @Override
    public Singer save(final Singer singer) {
        final Singer singerB = new Singer();
        singerB.setFirstName(singer.getFirstName());
        singerB.setLastName(singer.getLastName());

        if (singer.getId() == null) {
            emA.persist(singer);

            // rollback demo: emA will be rolled back
            // if (true){
            //    throw new JpaSystemException(new PersistenceException("Something went wrong"));
            // }

            emB.persist(singerB);
        } else {
            emA.merge(singer);
            emB.merge(singerB);
        }

        return singer;
    }

    @Override
    public long countAll() {
        throw new NotImplementedException();
    }


    private List<Singer> findAllInA() {
        return emA.createQuery(FIND_ALL).getResultList();
    }

    private List<Singer> findAllInB() {
        return emB.createQuery(FIND_ALL).getResultList();
    }
}

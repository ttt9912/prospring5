package ch8.p1_jpa.service;

import ch8.entity.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/*
 * @PersistenceContext: entity manager injection.
 *      unitName: optional parameter
 *
 * Typically one persistence unit represents an individual DataSource
 */
@Service("jpaSingerService")
@Repository
@Transactional
public class SingerServiceImpl implements SingerService {
    private static Logger logger = LoggerFactory.getLogger(SingerServiceImpl.class);

    private final String ALL_SINGERS_NATIVE_QUERY = "select id, first_name, last_name, birth_date, version from singer";

    @PersistenceContext
    private EntityManager em;


    @Transactional(readOnly = true)
    @Override
    public List<Singer> findAll() {
        return em.createNamedQuery("Singer.findAll", Singer.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Singer> findAllWithAlbums() {
        return em.createNamedQuery("Singer.findAllWithAlbums", Singer.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public Singer findById(final Long id) {
        TypedQuery<Singer> query = em.createNamedQuery("Singer.findById", Singer.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public Singer save(final Singer singer) {

        if (singer.getId() == null) {
            logger.info("Inserting a new singer");
            em.persist(singer); // makes singer a managed instance
        } else {
            logger.info("Updating an existing singer");
            em.merge(singer);
        }

        logger.info("Saved singer with id={}", singer.getId());
        return singer;
    }

    @Override
    public void delete(final Singer singer) {
        // em.merge is invoked first to merge the state of the entity
        // into the current persistence context.

        Singer mergedSinger = em.merge(singer);
        em.remove(mergedSinger);
        logger.info("Deleted singer with id={}", singer.getId());
    }

    @Transactional(readOnly = true)
    @Override
    public List<Singer> findAllByNativeQuery() {
        // map ResultSet back to the orm entity class

        return em.createNativeQuery(
                ALL_SINGERS_NATIVE_QUERY, Singer.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Singer> findAllByNativeQuery_ResultSetMapping() {
        // map result set with @SqlResultSetMapping name=singerResult (defined on entity)

        return em.createNativeQuery(
                ALL_SINGERS_NATIVE_QUERY, "singerResult")
                .getResultList();
    }
}

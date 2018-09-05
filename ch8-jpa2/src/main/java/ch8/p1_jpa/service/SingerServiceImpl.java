package ch8.p1_jpa.service;

import ch8.entity.Singer;
import ch8.entity.Singer_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
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

    // --------------------------------------------------------------
    // JPA Named Queries
    // --------------------------------------------------------------

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

    // --------------------------------------------------------------
    // persist, merge, delete
    // --------------------------------------------------------------

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


    // --------------------------------------------------------------
    // JPA Native Queries
    // --------------------------------------------------------------

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


    // --------------------------------------------------------------
    // JPA Criteria API
    // --------------------------------------------------------------

    @Override
    public List<Singer> findByCriteriaQuery(final String firstName, final String lastName) {
        logger.info("Finding singer for firstName: {} and lastName: {}",
                firstName, lastName);

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Singer> criteriaQuery = criteriaBuilder.createQuery(Singer.class);

        Root<Singer> singerRoot = criteriaQuery.from(Singer.class);
        singerRoot.fetch(Singer_.albums, JoinType.LEFT);
        singerRoot.fetch(Singer_.instruments, JoinType.LEFT);
        criteriaQuery.select(singerRoot).distinct(true);
        Predicate criteria = criteriaBuilder.conjunction();

        if (firstName != null) {
            Predicate p = criteriaBuilder.equal(singerRoot.get(Singer_.firstName), firstName);
            criteria = criteriaBuilder.and(criteria, p);
        }

        if (lastName != null) {
            Predicate p = criteriaBuilder.equal(singerRoot.get(Singer_.lastName), lastName);
            criteria = criteriaBuilder.and(criteria, p);
        }

        criteriaQuery.where(criteria);
        return em.createQuery(criteriaQuery).getResultList();
    }


}

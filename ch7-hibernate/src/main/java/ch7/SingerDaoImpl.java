package ch7;

import ch7.entity.Singer;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/*
 * @Transactional von 'org.springframework.' anstatt javax importieren!
 *
 * SQL: query against database
 * HQL: query against domain model
 *
 * Entities are not joined by default (= lazy loading). Use
 * 'join fetch' for eager loading.
 */

@Transactional
@Repository("singerDao")
class SingerDaoImpl implements SingerDao {
    private static final Logger logger = LoggerFactory.getLogger(SingerDaoImpl.class);

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Resource(name = "sessionFactory")
    public void setSessionFactory(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Transactional(readOnly = true)
    public List<Singer> findAll() {
        // LAZY FETCHING: Does not load relationships

        return sessionFactory.getCurrentSession()
                .createQuery("from Singer s")
                .list();
    }

    @Transactional(readOnly = true)
    public List<Singer> findAllWithAlbums() {
        return sessionFactory.getCurrentSession()
                .getNamedQuery("Singer.findAllWithAlbums")
                .list();
    }

    @Transactional(readOnly = true)
    public Singer findById(final Long id) {
        return (Singer) sessionFactory.getCurrentSession()
                .getNamedQuery("Singer.findById")
                .setParameter("id", id)
                .uniqueResult();
    }

    @Override
    public Singer save(final Singer singer) {
        sessionFactory.getCurrentSession()
                .saveOrUpdate(singer);
        return singer;
    }

    @Override
    public void delete(final Singer singer) {
        sessionFactory.getCurrentSession().delete(singer);
        logger.info("Deleted singer with id {}", singer.getId());
    }
}

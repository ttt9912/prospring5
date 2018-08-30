package ch7;

import ch7.entity.Singer;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/*
 * @Transactional von 'org.springframework.' anstatt javax importieren!
 *
 * SQL: query against database
 * HQL: query against domain model
 */

@Transactional
@Repository("singerDao")
class SingerDaoImpl implements SingerDao {

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

    @Override
    public Singer findById(final Long id) {
        return (Singer) sessionFactory.getCurrentSession()
                .getNamedQuery("Singer.findById")
                .setParameter("id", id)
                .uniqueResult();
    }
}

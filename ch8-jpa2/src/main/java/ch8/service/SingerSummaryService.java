package ch8.service;

import ch8.view.SummarySinger;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Iterator;
import java.util.List;

@Service("singerSummaryUntyped")
@Repository
@Transactional
public class SingerSummaryService {

    @PersistenceContext
    private EntityManager em;

    /*
     * manually manipulate the ResultSet
     *  - row        => Object[]
     *  - result set => Iterator<Object[]>
     */
    @Transactional(readOnly = true)
    public void displayAllUntyped() {
        List result = em.createQuery(
                "select s.firstName, s.lastName, a.title " +
                        "from Singer s left join s.albums a " +
                        "where a.releaseDate=(select max(a2.releaseDate) " +
                        "from Album a2 where a2.singer.id = s.id)")
                .getResultList();

        int count = 0;

        for (Iterator i = result.iterator(); i.hasNext(); ) {
            Object[] values = (Object[]) i.next();
            System.out.println(++count + ": " + values[0] + ", " + values[1] + ", " + values[2]);
        }
    }


    /*
     * map result set to custom view object via constructor
     */
    @Transactional(readOnly = true)
    public List<SummarySinger> findAllView() {

        return em.createQuery(
                "select new ch8.view.SummarySinger(s.firstName, s.lastName, a.title) " +
                        "from Singer s left join s.albums a " +
                        "where a.releaseDate=(select max(a2.releaseDate) " +
                        "from Album a2 where a2.singer.id = s.id)", SummarySinger.class)
                .getResultList();
    }
}

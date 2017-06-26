package ru.kirkazan.esadykov.investigation.hibernate52;

import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.SQLException;


/**
 * @author ser
 * @since 31.05.17 15:11
 */
public class EMNPlusOneTest {
    private static Logger logger = LoggerFactory.getLogger(EMNPlusOneTest.class);

    private EntityManagerFactory factory;
    private EntityManager manager;

    static { //runs when the main class is loaded.
        System.setProperty("logback-access.debug", "true");
        System.setProperty("org.jboss.logging.provider", "slf4j");
    }

    @Test
    public void test() {
//        manager.clear();
//        TypedQuery<MasterEntity> q = manager.createQuery("select m from MasterEntity m", MasterEntity.class);
//        logger.debug(q.getResultList().toString());
        manager.clear();
        TypedQuery<MasterEntity> query2 = manager.createQuery("select m from MasterEntity m join fetch m.ref", MasterEntity.class);
        logger.debug(query2.getResultList().toString());
//        manager.clear();
//        CriteriaBuilder cb = manager.getCriteriaBuilder();
//        CriteriaQuery<MasterEntity> q = cb.createQuery(MasterEntity.class);
//        Root<MasterEntity> root = q.from(MasterEntity.class);
//        root.fetch("ref");
//        manager.createQuery(q).getResultList();

    }

    @Before
    public void init() throws SQLException {
        factory = Persistence.createEntityManagerFactory("ru.kirkazan.esadykov.investigation.hibernate52");
        manager = factory.createEntityManager();
/*
        manager.getTransaction().begin();

        RefEntity ref1 = new RefEntity("ref1");
        manager.persist(ref1);
        RefEntity ref2 = new RefEntity("ref2");
        manager.persist(ref2);
        RefEntity ref3 = new RefEntity("ref3");
        manager.persist(ref3);
        RefEntity ref4 = new RefEntity("ref4");
        manager.persist(ref4);

        for (int i = 1; i < 100; i++) {
            MasterEntity m = new MasterEntity(ref1);
            manager.persist(m);
            m = new MasterEntity(ref2);
            manager.persist(m);
            m = new MasterEntity(ref3);
            manager.persist(m);
            m = new MasterEntity(ref4);
            manager.persist(m);
        }
        manager.getTransaction().commit();
*/

    }


    @After
    public void destroy() {
        logger.info("Destroying...");
        if (manager != null) {
            manager.close();
            logger.info("manager closed");
        }
        if (factory != null) {
            factory.close();
            logger.info("factory closed");
        }
        logger.info("destroyed!");
    }

}

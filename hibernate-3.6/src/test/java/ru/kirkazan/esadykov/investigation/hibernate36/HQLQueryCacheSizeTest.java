package ru.kirkazan.esadykov.investigation.hibernate36;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.hibernate.impl.SessionFactoryImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ser
 * @since 19.02.14 22:27
 */
public class HQLQueryCacheSizeTest {

    private Logger logger = LoggerFactory.getLogger(HQLQueryCacheSizeTest.class);

    SessionFactory sessionFactory;
    Session session;

    @Before
    public void init() {
        sessionFactory = new Configuration().configure().buildSessionFactory();

        session = sessionFactory.openSession();
    }

    @After
    public void destroy() {
        logger.info("Destroying...");
        if (session != null) {
            session.close();
            logger.info("session closed");
        }
        if (sessionFactory != null) {
            sessionFactory.close();
            logger.info("factory closed");
        }
        logger.info("destroyed!");
    }

    @Test
    public void testCacheSize() {
        TestEntity teSource = new TestEntity(1);
        session.persist(teSource);
        TestEntity teTarget = (TestEntity) session.get(TestEntity.class, teSource.getId());
        Assert.assertEquals(teSource.getId(), teTarget.getId());
        session.flush();
        logger.debug(session.createQuery("from TestEntity").list().toString());
        logger.debug(session.createQuery("from TestEntity t where t.id = 1").list().toString());
        logger.debug(session.createQuery("from TestEntity t where t.id = 2").list().toString());
        logger.debug(session.createQuery("from TestEntity").list().toString());
        logger.debug(session.createQuery("from TestEntity t where t.id = 1").list().toString());
        logger.debug(session.createQuery("from TestEntity t where t.id = 2").list().toString());
    }

}

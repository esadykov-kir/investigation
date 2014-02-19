package ru.kirkazan.esadykov.investigation.hibernate36;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author ser
 * @since 20.02.14 0:33
 */
public class EMCreationTest {
    private Logger logger = LoggerFactory.getLogger(EMCreationTest.class);

    EntityManagerFactory factory;
    EntityManager manager;

    @Before
    public void init() {
        factory = Persistence.createEntityManagerFactory("ru.kirkazan.esadykov.investigation.hibernate36");
        manager = factory.createEntityManager();
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

    @Test
    public void testCacheSize() {

        manager.getTransaction().begin();
        TestEntity teSource = new TestEntity(1);
        manager.persist(teSource);
        TestEntity teTarget = manager.find(TestEntity.class, teSource.getId());
        Assert.assertEquals(teSource.getId(), teTarget.getId());
        manager.flush();
        logger.debug(manager.createQuery("from TestEntity").getResultList().toString());
        logger.debug(manager.createQuery("from TestEntity t where t.id = 1").getResultList().toString());
        logger.debug(manager.createQuery("from TestEntity t where t.id = 2").getResultList().toString());
        logger.debug(manager.createQuery("from TestEntity").getResultList().toString());
        logger.debug(manager.createQuery("from TestEntity t where t.id = 1").getResultList().toString());
        logger.debug(manager.createQuery("from TestEntity t where t.id = 2").getResultList().toString());
        manager.getTransaction().commit();

    }
}

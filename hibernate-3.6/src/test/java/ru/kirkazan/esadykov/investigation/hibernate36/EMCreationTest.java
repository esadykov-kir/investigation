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


    @Test
    public void testCache() {

        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();

        logger.debug("--persist");
        TestEntity teSource = new TestEntity(1);
        manager.persist(teSource);
        manager.flush();

        logger.debug("--find exists in same manager");
        TestEntity teTarget = manager.find(TestEntity.class, teSource.getId());
        Assert.assertEquals(teSource.getId(), teTarget.getId());


        logger.debug("--find exists in same manager");
        teTarget = manager.find(TestEntity.class, teSource.getId());
        Assert.assertEquals(teSource.getId(), teTarget.getId());

        logger.debug("--find exists in same manager");
        teTarget = manager.find(TestEntity.class, teSource.getId());
        Assert.assertEquals(teSource.getId(), teTarget.getId());

        manager.getTransaction().commit();
        manager.close();

        EntityManager anotherManager = factory.createEntityManager();
        anotherManager.getTransaction().begin();

        logger.debug("--find exists");
        teTarget = anotherManager.find(TestEntity.class, teSource.getId());
        Assert.assertEquals(teSource.getId(), teTarget.getId());

        logger.debug("--find exists again");
        teTarget = anotherManager.find(TestEntity.class, teSource.getId());
        Assert.assertEquals(teSource.getId(), teTarget.getId());

        logger.debug("--find exists again");
        teTarget = anotherManager.find(TestEntity.class, teSource.getId());
        Assert.assertEquals(teSource.getId(), teTarget.getId());

        logger.debug("--find not exists");
        Assert.assertNull(anotherManager.find(TestEntity.class, 2));

        logger.debug("--find not exists");
        Assert.assertNull(anotherManager.find(TestEntity.class, 2));

        anotherManager.getTransaction().commit();
        anotherManager.close();
    }
}

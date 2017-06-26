package ru.kirkazan.esadykov.investigation.hibernate36;

import org.junit.After;
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
        EntityManagerHolder.setManager(manager);
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
    public void testVersions() {
        manager.getTransaction().begin();

        TestEntity entity = new TestEntity();
        manager.persist(entity);
        entity = manager.find(TestEntity.class, entity.getId());
        logger.info("entity version id={}, entityId={}", entity.getId(), entity.getEntityId());

        TestEntity newEntity = new TestEntity();
        newEntity.setEntityId(entity.getEntityId());
        manager.persist(newEntity);
        newEntity = manager.find(TestEntity.class, newEntity.getId());
        logger.info("entity version id={}, entityId={}", newEntity.getId(), newEntity.getEntityId());


        manager.getTransaction().commit();

    }
/*
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

    */

/*
    @Test
    public void testForRushan() {

        factory = Persistence.createEntityManagerFactory("ru.kirkazan.esadykov.investigation.hibernate52");
        manager = factory.createEntityManager();

        Test2Entity entity2 = new Test2Entity(22);
        TestEntity entity = new TestEntity(11);
        entity.setEntity(entity2);
        manager.getTransaction().begin();
        manager.persist(entity2);
        manager.persist(entity);
        manager.flush();
        manager.getTransaction().commit();

        manager.close();

        //factory.close();
        //factory = Persistence.createEntityManagerFactory("ru.kirkazan.esadykov.investigation.hibernate52");

        manager = factory.createEntityManager();

//        entity2 = manager.find(Test2Entity.class, 22);
//        logger.debug("22: {}", entity2);

        entity = manager.find(TestEntity.class, 11);
        logger.debug("11: {}", entity);
//        manager.close();
        logger.debug("22: {}", entity.getEntity());


        manager.close();
        factory.close();


    }

*/
/*
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
    */
}

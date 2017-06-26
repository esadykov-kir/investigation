package ru.kirkazan.esadykov.investigation.hibernate52;

import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.*;

/**
 * @author ser
 * @since 20.02.14 0:33
 */
@FixMethodOrder
public class EMCreationTest {
    public static final int ITERATIONS = 100;
    private static Logger logger = LoggerFactory.getLogger(EMCreationTest.class);

    EntityManagerFactory factory;
    EntityManager manager;

    @BeforeClass
    public static void cleanupBefore() throws SQLException {
        Connection connection = getConnection();
        Statement stat = connection.createStatement();
        stat.executeUpdate("truncate version_long");
        stat.executeUpdate("truncate version_uuid_rnd");
        stat.executeUpdate("truncate version_uuid_seq");
        stat.executeUpdate("truncate version_varchar_rnd");
        stat.executeUpdate("truncate version_varchar_seq");

        stat.execute("select pg_stat_statements_reset()");
    }

    @Before
    public void init() throws SQLException {
        factory = Persistence.createEntityManagerFactory("ru.kirkazan.esadykov.investigation.hibernate52");
        manager = factory.createEntityManager();

    }

    private static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:postgresql://localhost:54946/postgres", "postgres", "postgres");
        } catch (SQLException e) {
            logger.error("Connection error", e);
        }
        return null;
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
        logger.info("Start...");
        long startAt = System.currentTimeMillis();
        for (int i = 1; i <= ITERATIONS; i++) {
            manager.getTransaction().begin();

            TestEntity entity = new TestEntity();
            entity.setValue("1");
            manager.persist(entity);

            manager.getTransaction().commit();
            manager.clear();
        }
        logger.info("..finish. It takes {} ms", System.currentTimeMillis() - startAt);
    }


    @Test
    public void testVersionsUUIDRandom() {
        logger.info("Start...");
        long startAt = System.currentTimeMillis();
        for (int i = 1; i <= ITERATIONS; i++) {
            manager.getTransaction().begin();

            TestUUIDRandomEntity entity = new TestUUIDRandomEntity();
            entity.setValue("1");
            manager.persist(entity);

            manager.getTransaction().commit();
            manager.clear();
        }
        logger.info("..finish. It takes {} ms", System.currentTimeMillis() - startAt);
    }

    @Test
    public void testVersionsUUIDSeq() {
        logger.info("Start...");
        long startAt = System.currentTimeMillis();
        for (int i = 1; i <= ITERATIONS; i++) {
            manager.getTransaction().begin();

            TestUUIDSeqEntity entity = new TestUUIDSeqEntity();
            entity.setValue("1");
            manager.persist(entity);

            manager.getTransaction().commit();
            manager.clear();
        }
        logger.info("..finish. It takes {} ms", System.currentTimeMillis() - startAt);
    }

    @Test
    public void testVersionsVarCharRnd() {
        logger.info("Start...");
        long startAt = System.currentTimeMillis();
        for (int i = 1; i <= ITERATIONS; i++) {
            manager.getTransaction().begin();

            TestVarCharRandomEntity entity = new TestVarCharRandomEntity();
            entity.setValue("1");
            manager.persist(entity);

            manager.getTransaction().commit();
            manager.clear();
        }
        logger.info("..finish. It takes {} ms", System.currentTimeMillis() - startAt);
    }

    @Test
    public void testVersionsVarCharSeq() {
        logger.info("Start...");
        long startAt = System.currentTimeMillis();
        for (int i = 1; i <= ITERATIONS; i++) {
            manager.getTransaction().begin();

            TestVarCharSeqEntity entity = new TestVarCharSeqEntity();
            entity.setValue("1");
            manager.persist(entity);

            manager.getTransaction().commit();
            manager.clear();
        }
        logger.info("..finish. It takes {} ms", System.currentTimeMillis() - startAt);
    }


    public void testVersionsNoHiber() throws SQLException {
        Connection connection = getConnection();
        connection.setAutoCommit(false);
        PreparedStatement stat = connection.prepareStatement("insert into version (id, entityId, value) values (?,?,?)");
        for (int i = 0; i < 1000; i++) {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            stat.setInt(1, i);
            stat.setInt(2, i);
            stat.setString(3,"1");

            stat.executeUpdate();
            connection.commit();
        }
        logger.info("Start...");
        long startAt = System.currentTimeMillis();
        for (int i = 1001; i < 51001; i++) {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            stat.setInt(1, i);
            stat.setInt(2, i);
            stat.setString(3, "1");

            stat.executeUpdate();
            connection.commit();
        }
        logger.info("..finish. It takes {} ms", System.currentTimeMillis() - startAt);


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

package ru.kirkazan.esadykov.investigation.tomcatpool;

import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author esadykov
 * @since 05.03.14 18:14
 */
public class ValidationIntervalShow
{
    public static final int THREADS_COUNT = 100;
    public static final int BUSY_SECS = 100;

    @Test
    public void show() throws Exception
    {
        /*
        <Resource name="jdbc/lsd_slave" auth="Container"
        type="javax.sql.DataSource" driverClassName="org.postgresql.Driver"
        url="jdbc:postgresql://192.168.2.200:5432/lsd_zab"
        username="app" password="app_zab"
        validationQuery="select 1" testOnBorrow="true" factory="org.apache.tomcat.jdbc.pool.DataSourceFactory" validationInterval="30000"
        maxActive="60" maxIdle="5" maxWait="60000"/>
        */

        PoolProperties props = new PoolProperties();
        props.setDriverClassName("org.postgresql.Driver");
        props.setUrl("jdbc:postgresql://localhost:5432/postgres");
        props.setUsername("postgres");
        props.setPassword("postgres");
        props.setValidationQuery("select 1");
        props.setValidationInterval(30000);
        props.setTestOnBorrow(true);
        props.setMaxActive(60);
        props.setMaxIdle(10);
        props.setMaxWait(60000);
        props.setLogAbandoned(true);

        final DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource(props);

        Runnable r = new Runnable()
        {
            @Override
            public void run()
            {
                Connection c = null;
                Statement s = null;
                try
                {
                    c = ds.getConnection();
                    c.setAutoCommit(false);
                    s = c.createStatement();
                    long start = System.currentTimeMillis();
                    s.execute("begin;");
                    while (System.currentTimeMillis() - start <= BUSY_SECS*1000)
                    {
                        s.execute("select 1;");

                        try
                        {
                            Thread.sleep(1000);
                        }
                        catch (InterruptedException ex)
                        {
                            Thread.currentThread().interrupt();
                        }
                    }
                    c.commit();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    try
                    {
                        if (s != null && !s.isClosed())
                            s.close();
                    }
                    catch (SQLException e)
                    {
                    }

                    try
                    {
                        if (c != null && !c.isClosed())
                            c.close();
                    }
                    catch (SQLException e)
                    {
                    }
                }
            }
        };
        Thread[] threads = new Thread[THREADS_COUNT];
        for(int i = 0; i < THREADS_COUNT; i++)
        {
            threads[i] = (new Thread(r));
            threads[i].start();
        }

        for(int i = 0; i < THREADS_COUNT; i++)
        {
            threads[i].join();
        }


    }

}

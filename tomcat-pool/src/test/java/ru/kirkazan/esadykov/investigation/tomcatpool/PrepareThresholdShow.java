package ru.kirkazan.esadykov.investigation.tomcatpool;

import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.junit.Test;
import org.postgresql.Driver;
import org.postgresql.PGStatement;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author esadykov
 * @since 05.03.14 18:14
 */
public class PrepareThresholdShow {
    public static final int BUSY_SECS = 100;

    @Test
    public void show() throws Exception {
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
        props.setUrl("jdbc:postgresql://localhost:5432/postgres?prepareThreshold=4&logLevel=2");
        props.setUsername("postgres");
        props.setPassword("postgres");

        DriverManager.setLogWriter(new PrintWriter(System.out));
        ((Driver) DriverManager.getDriver("jdbc:postgresql://localhost:5432/postgres?prepareThreshold=0&logLevel=2")).setLogLevel(2);
        final DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource(props);
        Connection c = null;
        PreparedStatement s = null;
        try {

            c = ds.getConnection();
            s = c.prepareStatement("select ?;");
            PGStatement pgs = (PGStatement) s;
            s.setInt(1,1);

            System.out.println(pgs.isUseServerPrepare());
            s.execute();
            System.out.println(pgs.isUseServerPrepare());
            s.execute();
            System.out.println(pgs.isUseServerPrepare());
            s.execute();
            System.out.println(pgs.isUseServerPrepare());
            s.execute();
            System.out.println(pgs.isUseServerPrepare());
            s.execute();
            System.out.println(pgs.isUseServerPrepare());
            s.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (s != null && !s.isClosed())
                    s.close();
            } catch (SQLException e) {
            }

            try {
                if (c != null && !c.isClosed())
                    c.close();
            } catch (SQLException e) {
            }
        }
    }

}

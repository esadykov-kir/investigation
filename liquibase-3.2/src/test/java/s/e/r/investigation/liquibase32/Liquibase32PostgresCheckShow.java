package s.e.r.investigation.liquibase32;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author esadykov
 * @since 31.07.2014 18:47
 */
public class Liquibase32PostgresCheckShow {
    Connection connection;

    @Before
    public void before() throws SQLException
    {
        connection = DriverManager.getConnection("jdbc:postgresql://192.168.12.11:5432/lsd_rmis", "iehr", "iehr");
    }
    @After
    public void after() throws SQLException
    {
        if (connection == null)
            return;

        connection.close();
    }

    @Test
    public void showBadChecks() throws LiquibaseException
    {
        Database db = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
        Liquibase lb = new Liquibase(
                "liquibase32/liquibase-3.2-bad-postgres-check-show.xml",
                new ClassLoaderResourceAccessor(),
                db
        );
        lb.update("");
    }

    @Test
    public void showGoodChecks() throws SQLException, LiquibaseException
    {
        Database db = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
        Liquibase lb = new Liquibase(
                "liquibase32/liquibase-3.2-good-postgres-check-show.xml",
                new ClassLoaderResourceAccessor(),
                db
        );
        lb.update("");
    }
}

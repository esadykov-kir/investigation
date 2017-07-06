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
 * @since 01.08.2014 18:47
 */
public class LiquibaseOrderShow {
    private Connection connection;

    @Before
    public void before() throws SQLException
    {
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:54946/postgres", "postgres", "postgres");
    }
    @After
    public void after() throws SQLException
    {
        if (connection == null)
            return;

        connection.close();
    }

    @Test
    public void order() throws LiquibaseException
    {
        Database db = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
        Liquibase lb = new Liquibase(
                "order/master.xml",
                new ClassLoaderResourceAccessor(),
                db
        );
        lb.update("");
    }


}

package ru.kirkazan.esadykov.investigation.liquibase31;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;

/**
 * @author esadykov
 * @since 24.02.14 12:42
 */
public class Liquibase31PostgresCheckShow
{

    Connection connection;

    @Before
    public void before() throws SQLException
    {
        connection = DriverManager.getConnection("jdbc:postgresql://192.168.3.40:5432/lsd_20120512_old", "iehr", "iehr");
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
                "ru/kirkazan/esadykov/investigation/liquibase31/liquibase-3.1-bad-postgres-check-show.xml",
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
                "ru/kirkazan/esadykov/investigation/liquibase31/liquibase-3.1-good-postgres-check-show.xml",
                new ClassLoaderResourceAccessor(),
                db
        );
        lb.update("");
    }


}

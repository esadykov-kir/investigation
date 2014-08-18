package ru.kirkazan.esadykov.investigation.liquibase20;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.junit.Test;

import java.sql.*;

/**
 * @author esadykov
 * @since 24.02.14 12:42
 */
public class Liquibase20PostgresCheckShow
{
    @Test
    public void testPostgreSQL() throws SQLException, LiquibaseException
    {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://192.168.3.40:5432/lsd_20120512_old", "iehr", "iehr");
        Database db = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
        Liquibase lb = new Liquibase(
                "ru/kirkazan/esadykov/investigation/liquibase20/liquibase-2.0-postgres-check-show.xml",
                new ClassLoaderResourceAccessor(),
                db
        );
        lb.update("");
    }

}

package ru.kirkazan.esadykov.investigation.liquibase20;

import liquibase.change.custom.CustomSqlChange;
import liquibase.change.custom.CustomTaskChange;
import liquibase.database.Database;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.CustomChangeException;
import liquibase.exception.SetupException;
import liquibase.exception.ValidationErrors;
import liquibase.resource.ResourceAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;

/**
 * @author esadykov
 * @since 27.03.14 20:29
 */
public class SampleCustomChange implements CustomTaskChange
{
    private String param;
    private String param2;
    private String param3;
    private String param4;
    private String param5;

    public void setParam(String param)
    {
        this.param = param;
    }


    public void setParam2(String param2)
    {
        this.param2 = param2;
    }

    public void setParam3(String param3)
    {
        this.param3 = param3;
    }

    public void setParam4(String param4)
    {
        this.param4 = param4;
    }

    public void setParam5(String param5)
    {
        this.param5 = param5;
    }

    Logger logger = LoggerFactory.getLogger("liquibase");
    @Override
    public void execute(Database database) throws CustomChangeException
    {
        logger.info("execute with param2 {}", param2);
        ResultSet resultSet;

    }

    @Override
    public String getConfirmationMessage()
    {
        return "All Clear";
    }

    @Override
    public void setUp() throws SetupException
    {

    }

    @Override
    public void setFileOpener(ResourceAccessor resourceAccessor)
    {

    }

    @Override
    public ValidationErrors validate(Database database)
    {
        return null;
    }
}

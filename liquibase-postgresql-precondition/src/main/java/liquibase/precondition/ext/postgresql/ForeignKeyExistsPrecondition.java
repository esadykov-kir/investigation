package liquibase.precondition.ext.postgresql;

import liquibase.changelog.ChangeSet;
import liquibase.changelog.DatabaseChangeLog;
import liquibase.database.Database;
import liquibase.exception.PreconditionErrorException;
import liquibase.exception.PreconditionFailedException;
import liquibase.exception.ValidationErrors;
import liquibase.exception.Warnings;
import liquibase.logging.LogFactory;
import liquibase.logging.Logger;
import liquibase.precondition.Precondition;
import liquibase.precondition.PreconditionFactory;

/**
 * @author ser
 * @since 25.02.14 22:54
 */
public class ForeignKeyExistsPrecondition implements Precondition {

    private Logger log = LogFactory.getInstance().getLog();

    static {
//        PreconditionFactory.getInstance().register(ForeignKeyExistsPrecondition.class);
    }

    @Override
    public String getName() {
        log.info("getName");
        return "foreignKeyConstraintExists";
    }

    @Override
    public Warnings warn(Database database) {
        log.info("warn");
        return null;
    }

    @Override
    public ValidationErrors validate(Database database) {
        log.info("validate");
        return null;
    }

    @Override
    public void check(Database database, DatabaseChangeLog databaseChangeLog, ChangeSet changeSet) throws PreconditionFailedException, PreconditionErrorException {
        log.info("check");
    }
}

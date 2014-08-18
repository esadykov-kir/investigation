import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author esadykov
 * @since 05.07.2014 22:31
 */
public class PG {

    private Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:postgresql://localhost/smira","smira", "smira");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

    }
}

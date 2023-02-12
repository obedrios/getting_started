
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author obedrios
 */
public class TestConnectionJDBC {

    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
            String urldb = "jdbc:postgresql://192.168.0.170:5432/sandboxdb";
            String user = "obedrios";
            String password = "darktiger";
            Connection connection = DriverManager.getConnection(urldb, user, password);
            if (connection.isClosed()) {
                System.out.println("Cant open connection");
            } else {
                System.out.println("Connection opened succesfully!!");
                connection.close();
            }
        } catch (ClassNotFoundException | SQLException exception) {
            throw new RuntimeException(exception);
        } finally {
            System.out.println("Good Bye!!");
        }
    }

}

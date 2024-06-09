import java.sql.Connection;
import java.sql.DriverManager;

public class KoneksiDB {
    private Connection conn = null;

    public Connection connect_to_db(String dbname, String userName, String password) {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname, userName, password);

            if (conn != null) {
                System.out.println("Terkoneksi");
            } else {
                System.out.println("Gagal terkoneksi");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return conn;
    }
}

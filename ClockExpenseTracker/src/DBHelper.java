import java.sql.*;

public class DBHelper {
    private static final String URL = "jdbc:mysql://localhost:3306/expense_tracker"; // your DB name
    private static final String USER = "root";      // your MySQL username
    private static final String PASSWORD = "2311";  // your MySQL password

    public static Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // MySQL driver
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void setupDatabase() {
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {

            String sql = "CREATE TABLE IF NOT EXISTS expenses (" +
                         "id INT AUTO_INCREMENT PRIMARY KEY," +
                         "category VARCHAR(100) NOT NULL," +
                         "amount DOUBLE NOT NULL," +
                         "date DATE NOT NULL)";
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

import java.sql.*;
import java.util.ArrayList;

public class ExpenseManager {

    public static void addExpense(String category, double amount, String date) {
        try (Connection conn = DBHelper.connect();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO expenses (category, amount, date) VALUES (?, ?, ?)")) {
            stmt.setString(1, category);
            stmt.setDouble(2, amount);
            stmt.setString(3, date);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Expense> getAllExpenses() {
        ArrayList<Expense> list = new ArrayList<>();
        try (Connection conn = DBHelper.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM expenses")) {

            while (rs.next()) {
                list.add(new Expense(
                        rs.getInt("id"),
                        rs.getString("category"),
                        rs.getDouble("amount"),
                        rs.getString("date")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void deleteExpense(int id) {
        try (Connection conn = DBHelper.connect();
             PreparedStatement stmt = conn.prepareStatement(
                     "DELETE FROM expenses WHERE id = ?")) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

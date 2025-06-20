import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

public class ExpenseTrackerGUI extends JFrame {
    private JTextField categoryField, amountField, dateField;
    private JLabel clockLabel;
    private DefaultTableModel tableModel;

    public ExpenseTrackerGUI() {
        DBHelper.setupDatabase();
        setTitle("Expense Tracker");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Add Expense"));

        inputPanel.add(new JLabel("Category:"));
        categoryField = new JTextField();
        inputPanel.add(categoryField);

        inputPanel.add(new JLabel("Amount:"));
        amountField = new JTextField();
        inputPanel.add(amountField);

        inputPanel.add(new JLabel("Date (YYYY-MM-DD):"));
        dateField = new JTextField(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        inputPanel.add(dateField);

        JButton addBtn = new JButton("Add");
        addBtn.addActionListener(e -> addExpense());
        inputPanel.add(addBtn);

        JButton deleteBtn = new JButton("Delete Selected");
        deleteBtn.addActionListener(e -> deleteSelected());
        inputPanel.add(deleteBtn);

        tableModel = new DefaultTableModel(new String[]{"ID", "Category", "Amount", "Date"}, 0);
        JTable table = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(table);

        clockLabel = new JLabel("", JLabel.CENTER);
        clockLabel.setFont(new Font("Monospaced", Font.BOLD, 16));
        startClock();

        add(clockLabel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.WEST);
        add(tableScroll, BorderLayout.CENTER);

        loadExpenses();
        setVisible(true);
    }

    private void startClock() {
        Timer timer = new Timer(1000, e -> {
            clockLabel.setText("Current Time: " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
        });
        timer.start();
    }

    private void addExpense() {
        try {
            String category = categoryField.getText();
            double amount = Double.parseDouble(amountField.getText());
            String date = dateField.getText();

            ExpenseManager.addExpense(category, amount, date);
            loadExpenses();

            categoryField.setText("");
            amountField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid amount");
        }
    }

    private void loadExpenses() {
        tableModel.setRowCount(0);
        for (Expense e : ExpenseManager.getAllExpenses()) {
            tableModel.addRow(new Object[]{e.getId(), e.getCategory(), e.getAmount(), e.getDate()});
        }
    }

    private void deleteSelected() {
        int row = tableModel.getRowCount() > 0 ? tableModel.getRowCount() - 1 : -1;
        if (row != -1) {
            int id = (int) tableModel.getValueAt(row, 0);
            ExpenseManager.deleteExpense(id);
            loadExpenses();
        }
    }
}

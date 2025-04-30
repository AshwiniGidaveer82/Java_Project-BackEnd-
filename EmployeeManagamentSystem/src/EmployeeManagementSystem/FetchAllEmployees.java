package EmployeeManagementSystem;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.Color;

public class FetchAllEmployees {
    public static void main(String[] args) {
        JFrame frame = new JFrame("All Employees");
        frame.setForeground(new Color(255, 255, 0));
        frame.getContentPane().setBackground(new Color(0, 0, 0));
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] columns = {"ID", "Name", "Age", "Email", "Salary"};
        String[][] data = new String[0][];  // Initialize with no data
        JTable table = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 20, 550, 300);

        frame.getContentPane().add(scrollPane);
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);

        try (Connection con = DBConnection.getConnection()) {
            // Create a scrollable ResultSet by setting TYPE_SCROLL_INSENSITIVE
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT * FROM employees");

            rs.last(); // Move cursor to the last row to count rows
            int rowCount = rs.getRow();  // Get the number of rows
            rs.beforeFirst();  // Move the cursor back to the beginning

            String[][] employeeData = new String[rowCount][5];
            int i = 0;
            while (rs.next()) {
                employeeData[i][0] = String.valueOf(rs.getInt("id"));
                employeeData[i][1] = rs.getString("name");
                employeeData[i][2] = String.valueOf(rs.getInt("age"));
                employeeData[i][3] = rs.getString("email");
                employeeData[i][4] = String.valueOf(rs.getDouble("salary"));
                i++;
            }

            // Create a new JTable with fetched data
            JTable updatedTable = new JTable(employeeData, columns);
            updatedTable.setForeground(new Color(255, 255, 0));
            updatedTable.setBackground(new Color(0, 0, 0));
            scrollPane.setViewportView(updatedTable);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

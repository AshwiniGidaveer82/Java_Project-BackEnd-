package EmployeeManagementSystem;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.Color;

public class FetchEmployeeByID {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Fetch Employee by ID");
        frame.getContentPane().setBackground(new Color(0, 0, 0));
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel idLabel = new JLabel("Enter Employee ID:");
        idLabel.setForeground(new Color(255, 255, 0));
        idLabel.setBounds(50, 50, 150, 30);
        JTextField idField = new JTextField();
        idField.setBounds(200, 50, 100, 30);

        JTextArea resultArea = new JTextArea();
        resultArea.setBounds(50, 100, 300, 100);
        resultArea.setEditable(false);

        JButton fetchButton = new JButton("Fetch");
        fetchButton.setBounds(150, 220, 100, 30);

        frame.getContentPane().add(idLabel);
        frame.getContentPane().add(idField);
        frame.getContentPane().add(resultArea);
        frame.getContentPane().add(fetchButton);
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);

        fetchButton.addActionListener(e -> {
            int id = Integer.parseInt(idField.getText());

            try (Connection con = DBConnection.getConnection()) {
                String query = "SELECT * FROM employees WHERE id = ?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    String result = "ID: " + rs.getInt("id") + "\n" +
                                    "Name: " + rs.getString("name") + "\n" +
                                    "Age: " + rs.getInt("age") + "\n" +
                                    "Email: " + rs.getString("email") + "\n" +
                                    "Salary: " + rs.getDouble("salary");
                    resultArea.setText(result);
                } else {
                    resultArea.setText("Employee not found.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
}

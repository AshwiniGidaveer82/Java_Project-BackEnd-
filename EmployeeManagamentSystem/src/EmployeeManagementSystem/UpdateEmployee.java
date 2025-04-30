package EmployeeManagementSystem;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.Color;

public class UpdateEmployee {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Update Employee");
        frame.getContentPane().setBackground(new Color(0, 0, 0));
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel idLabel = new JLabel("ID:");
        idLabel.setForeground(new Color(255, 255, 0));
        idLabel.setBounds(50, 50, 100, 30);
        JTextField idField = new JTextField();
        idField.setBounds(150, 50, 200, 30);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setForeground(new Color(255, 255, 0));
        nameLabel.setBounds(50, 100, 100, 30);
        JTextField nameField = new JTextField();
        nameField.setBounds(150, 100, 200, 30);

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setForeground(new Color(255, 255, 0));
        ageLabel.setBounds(50, 150, 100, 30);
        JTextField ageField = new JTextField();
        ageField.setBounds(150, 150, 200, 30);

        JButton updateButton = new JButton("Update");
        updateButton.setBounds(150, 200, 100, 30);

        frame.getContentPane().add(idLabel);
        frame.getContentPane().add(idField);
        frame.getContentPane().add(nameLabel);
        frame.getContentPane().add(nameField);
        frame.getContentPane().add(ageLabel);
        frame.getContentPane().add(ageField);
        frame.getContentPane().add(updateButton);
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);

        updateButton.addActionListener(e -> {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());

            try (Connection con = DBConnection.getConnection()) {
                String query = "UPDATE employees SET name = ?, age = ? WHERE id = ?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, name);
                ps.setInt(2, age);
                ps.setInt(3, id);
                int rowsUpdated = ps.executeUpdate();

                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(frame, "Employee updated successfully.");
                } else {
                    JOptionPane.showMessageDialog(frame, "Employee not found.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
}

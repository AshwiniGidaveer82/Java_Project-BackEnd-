package EmployeeManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;

public class AddNewEmployee {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Add New Employee");
        frame.getContentPane().setBackground(new Color(0, 0, 0));
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add ID label and field
        JLabel idLabel = new JLabel("Employee ID:");
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

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setForeground(new Color(255, 255, 0));
        emailLabel.setBounds(50, 200, 100, 30);
        JTextField emailField = new JTextField();
        emailField.setBounds(150, 200, 200, 30);

        JLabel salaryLabel = new JLabel("Salary:");
        salaryLabel.setForeground(new Color(255, 255, 0));
        salaryLabel.setBounds(50, 250, 100, 30);
        JTextField salaryField = new JTextField();
        salaryField.setBounds(150, 250, 200, 30);

        JButton addButton = new JButton("Add Employee");
        addButton.setBounds(100, 300, 200, 30);

        frame.getContentPane().add(idLabel);
        frame.getContentPane().add(idField);
        frame.getContentPane().add(nameLabel);
        frame.getContentPane().add(nameField);
        frame.getContentPane().add(ageLabel);
        frame.getContentPane().add(ageField);
        frame.getContentPane().add(emailLabel);
        frame.getContentPane().add(emailField);
        frame.getContentPane().add(salaryLabel);
        frame.getContentPane().add(salaryField);
        frame.getContentPane().add(addButton);
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);

        addButton.addActionListener(e -> {
            try {
                // Get data from input fields
                int id = Integer.parseInt(idField.getText()); // Employee ID input
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String email = emailField.getText();
                double salary = Double.parseDouble(salaryField.getText());

                // Insert data into database
                try (Connection con = DBConnection.getConnection()) {
                    String query = "INSERT INTO employees (id, name, age, email, salary) VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement ps = con.prepareStatement(query);
                    ps.setInt(1, id); // Setting ID manually
                    ps.setString(2, name);
                    ps.setInt(3, age);
                    ps.setString(4, email);
                    ps.setDouble(5, salary);

                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(frame, "Employee Added Successfully!");
                    frame.dispose();
                    EmployeeHome.main(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Error Adding Employee");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter valid numbers for ID, Age, and Salary.");
            }
        });
    }
}


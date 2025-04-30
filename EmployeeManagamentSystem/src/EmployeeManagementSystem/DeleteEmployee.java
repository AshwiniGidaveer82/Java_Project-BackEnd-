package EmployeeManagementSystem;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.Color;

public class DeleteEmployee {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Delete Employee");
        frame.getContentPane().setBackground(new Color(0, 0, 0));
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel idLabel = new JLabel("Enter Employee ID to Delete:");
        idLabel.setForeground(new Color(255, 255, 0));
        idLabel.setBounds(50, 50, 200, 30);
        JTextField idField = new JTextField();
        idField.setBounds(250, 50, 100, 30);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(150, 100, 100, 30);

        frame.getContentPane().add(idLabel);
        frame.getContentPane().add(idField);
        frame.getContentPane().add(deleteButton);
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);

        deleteButton.addActionListener(e -> {
            int id = Integer.parseInt(idField.getText());

            try (Connection con = DBConnection.getConnection()) {
                String query = "DELETE FROM employees WHERE id = ?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, id);
                int rowsAffected = ps.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(frame, "Employee deleted successfully.");
                } else {
                    JOptionPane.showMessageDialog(frame, "Employee not found.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
}

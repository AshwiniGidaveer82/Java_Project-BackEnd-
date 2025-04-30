package EmployeeManagementSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class EmployeeHome {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Employee Management System");
        frame.getContentPane().setBackground(new Color(0, 0, 0));
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton fetchAllButton = new JButton("Fetch All Employees");
        fetchAllButton.setBackground(new Color(255, 255, 0));
        fetchAllButton.setBounds(100, 50, 200, 30);

        JButton fetchByIdButton = new JButton("Fetch Employee by ID");
        fetchByIdButton.setBackground(new Color(255, 255, 0));
        fetchByIdButton.setBounds(100, 100, 200, 30);

        JButton addButton = new JButton("Add New Employee");
        addButton.setBackground(new Color(255, 255, 0));
        addButton.setBounds(100, 150, 200, 30);

        JButton updateButton = new JButton("Update Employee");
        updateButton.setBackground(new Color(255, 255, 0));
        updateButton.setBounds(100, 200, 200, 30);

        JButton deleteButton = new JButton("Delete Employee");
        deleteButton.setBackground(new Color(255, 255, 0));
        deleteButton.setBounds(100, 250, 200, 30);

        frame.getContentPane().add(fetchAllButton);
        frame.getContentPane().add(fetchByIdButton);
        frame.getContentPane().add(addButton);
        frame.getContentPane().add(updateButton);
        frame.getContentPane().add(deleteButton);
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);

        fetchAllButton.addActionListener(e -> FetchAllEmployees.main(null));
        fetchByIdButton.addActionListener(e -> FetchEmployeeByID.main(null));
        addButton.addActionListener(e -> AddNewEmployee.main(null));
        updateButton.addActionListener(e -> UpdateEmployee.main(null));
        deleteButton.addActionListener(e -> DeleteEmployee.main(null));
    }
}

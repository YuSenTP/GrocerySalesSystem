package gui;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.MainFrame;
import data.GroceryItem;

public class ManagerInventory extends JPanel {
    private MainFrame main;
    private JTable inventoryTable;
    private DefaultTableModel tableColumns;
    private JButton backButton;
    private JScrollPane scrollPane;

    public ManagerInventory(MainFrame main) {
        this.main = main;
        setLayout(new BorderLayout());

        // Create table columns
        String[] columnNames = {"Item Name", "Price", "Quantity", "Category", "On Sale"};
        tableColumns = new DefaultTableModel(columnNames, 0);
        
        // Create table
        inventoryTable = new JTable(tableColumns);
        scrollPane = new JScrollPane(inventoryTable);
        add(scrollPane, BorderLayout.CENTER);
        
        // Bottom Panel 
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBackground(Color.WHITE);

        // Back Button
        backButton = new JButton("Back");
        backButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        backButton.setPreferredSize(new Dimension(100, 40));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.showManagerMenu();
            }
        });
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Populate table
        updateInventoryTable();
    }

    private void updateInventoryTable() {
        // Clear existing rows
        tableColumns.setRowCount(0);

        // Get updated inventory from controller
        GroceryItem[] inventory = main.getController().getInventory();

        // Populate table with inventory data
        for (GroceryItem item : inventory) {
            String onSaleStatus = item.getOnSale() ? "Yes" : "No";
            Object[] row = {
                item.getName(),
                "$" + item.getPrice(),
                item.getQuantity(),
                item.getCategory(),
                onSaleStatus
            };
            tableColumns.addRow(row);
        }
    }
}